package com.example.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.liveData
import androidx.paging.map
import com.example.data.local.database.AppDataBase
import com.example.data.network.mapper.Mapper
import com.example.data.network.models.CharacterDto
import com.example.data.network.paging.CharacterPagingSource
import com.example.data.network.source.ApiFactory
import com.example.data.network.source.ApiService
import com.example.domain.models.Character
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(application: Application) : Repository {

    private val mapper = Mapper()
    private val network = ApiFactory.apiService
    private val dao = AppDataBase.getInstance(application).characterDao()

    override suspend fun getCharactersFromNetWork(): LiveData<PagingData<Character>> =
        createPager(network).liveData.map { pagingData ->
            pagingData.map { characterDto ->
                mapper.mapFromDto(characterDto)
            }
        }

    override suspend fun getCharacterByIdFromNetWork(id: Int): Character {
        return try {
            mapper.mapFromDto(network.loadCharacter(id))
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchCharacterFromNetWork(name: String): List<Character> {
        return try {
            val searchList = network.searchCharacter(name).result
            mapper.mapListDtoToListDomain(searchList)
        } catch (e: Exception) {
            emptyList()
        }
    }


    override fun getCharactersFromDatabase(): LiveData<List<Character>> =
        MediatorLiveData<List<Character>>().apply {
            addSource(dao.getAllCharacters()) {
                value = mapper.mapListDbModelToListDomain(it)
            }
        }

    override fun isCharacterInFavorites(id: Int): LiveData<Boolean> =
        dao.isCharacterInFavorites(id)

    override suspend fun addToFavourites(character: Character) {
        dao.insertCharacter(mapper.mapToDbModel(character))
    }

    override suspend fun removeFromFavourites(id: Int) {
        dao.deleteCharacter(id)
    }

    private fun createPager(network: ApiService): Pager<Int, CharacterDto> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(network) }
        )
    }


    private companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 5
    }


    //  return try {
    //      val characterDtoList = network.loadCharacters(page = 2).result
    //      mapper.mapListDtoToListDomain(characterDtoList)
    //  } catch (e: Exception) {
    //      emptyList()
    //  }
}


