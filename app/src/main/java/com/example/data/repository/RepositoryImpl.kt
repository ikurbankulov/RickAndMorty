package com.example.data.repository

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.paging.map
import com.example.data.local.database.CharacterDao
import com.example.data.network.mapper.Mapper
import com.example.data.network.models.CharacterDto
import com.example.data.network.paging.CharacterPagingSource
import com.example.data.network.source.ApiFactory
import com.example.data.network.source.ApiService
import com.example.domain.models.Character
import com.example.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
        private val mapper: Mapper,
        private val dao: CharacterDao
) : Repository {

        override fun getCharactersFromNetWork(): LiveData<PagingData<Character>> =
                createPager(ApiFactory.apiService).liveData.map { pagingData ->
                        pagingData.map { characterDto ->
                                mapper.mapFromDto(characterDto)
                        }
                }

        override suspend fun getCharacterByIdFromNetWork(id: Int): Result<Character> {
                return try {
                        val response = ApiFactory.apiService.loadCharacter(id)
                        if (response.isSuccessful) {
                                response.body()?.let { characterDto ->
                                        Result.success(mapper.mapFromDto(characterDto))
                                } ?: Result.failure(NullPointerException("Character is null"))
                        } else {
                                Result.failure(NetworkErrorException())
                        }
                } catch (e: Exception) {
                        Result.failure(e)
                }
        }


        override suspend fun searchCharacterFromNetWork(name: String): List<Character> {
                return try {
                        val searchList = ApiFactory.apiService.searchCharacter(name).result
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

        private fun createPager(apiService: ApiService): Pager<Int, CharacterDto> {
                return Pager(config = PagingConfig(
                        pageSize = PAGE_SIZE,
                        prefetchDistance = PREFETCH_DISTANCE,
                        enablePlaceholders = false
                ), pagingSourceFactory = { CharacterPagingSource(apiService) })
        }

        private companion object {
                const val PAGE_SIZE = 20
                const val PREFETCH_DISTANCE = 5
        }
}


