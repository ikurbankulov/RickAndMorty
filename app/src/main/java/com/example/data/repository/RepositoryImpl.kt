package com.example.data.repository

import com.example.data.network.mapper.Mapper
import com.example.data.network.source.ApiFactory
import com.example.domain.models.Character
import com.example.domain.repository.Repository

class RepositoryImpl : Repository{

   private val mapper = Mapper()

    override suspend fun getCharacterList(): List<Character> {
        val characterList   = ApiFactory.apiService.loadCharacters(page = 2).result
        return characterList.map { it -> mapper.mapFromDto(it) }
    }

    override suspend fun getCharacterById(id: Int): Character {
        return mapper.mapFromDto(ApiFactory.apiService.loadCharacter(id))
    }

    override suspend fun searchCharacter(name: String): List<Character> {
        TODO("Not yet implemented")
    }
}