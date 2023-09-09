package com.example.domain.repository

import com.example.domain.models.Character

interface Repository {

    suspend fun getCharacterList(): List<Character>

    suspend fun getCharacterById(id: Int): Character

    suspend fun searchCharacter(name: String): List<Character>

}