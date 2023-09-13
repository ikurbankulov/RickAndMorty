package com.example.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.data.local.database.AppDataBase
import com.example.data.network.mapper.Mapper
import com.example.data.network.source.ApiFactory
import com.example.domain.models.Character
import com.example.domain.repository.Repository

class RepositoryImpl(application: Application) : Repository {

    private val mapper = Mapper()
    private val network = ApiFactory.apiService
    private val dao = AppDataBase.getInstance(application).characterDao()

    override suspend fun getCharactersFromNetWork(): List<Character> {
        return try {
            val characterDtoList = network.loadCharacters(page = 2).result
            mapper.mapListDtoToListDomain(characterDtoList)
        } catch (e: Exception) {
            emptyList()
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
}


