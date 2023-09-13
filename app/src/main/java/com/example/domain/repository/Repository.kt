package com.example.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.data.network.models.CharacterDto
import com.example.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharactersFromNetWork(): LiveData<PagingData<Character>>

    suspend fun getCharacterByIdFromNetWork(id: Int): Character

    suspend fun searchCharacterFromNetWork(name: String): List<Character>

    suspend fun addToFavourites(character: Character)

    suspend fun removeFromFavourites(id: Int)

    fun getCharactersFromDatabase(): LiveData<List<Character>>

    fun isCharacterInFavorites(id: Int): LiveData<Boolean>

}