package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.Character

interface Repository {

    suspend fun getCharactersFromNetWork(): List<Character>

    suspend fun getCharacterByIdFromNetWork(id: Int): Character

    suspend fun searchCharacterFromNetWork(name: String): List<Character>

    suspend fun getCharactersFromDatabase(): LiveData<List<Character>>

    suspend fun addToFavourites(character: Character)

    suspend fun removeFromFavourites(id: Int)

}