package com.example.data.network


import com.example.data.models.CharacterEntity
import com.example.data.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun loadCharacters(): CharacterResponse

    @GET("character/{id}")
    suspend fun loadCharacter(@Path("id") id: Int): CharacterEntity

suspend fun searchCharacter(name: String): List<CharacterEntity>
}
