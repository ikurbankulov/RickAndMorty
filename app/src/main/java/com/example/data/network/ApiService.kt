package com.example.data.network


import com.example.data.models.CharacterEntity
import com.example.data.models.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun loadCharacters(@Query("page") page: Int): Response

    @GET("character/{id}")
    suspend fun loadCharacter(@Path("id") id: Int): CharacterEntity

suspend fun searchCharacter(name: String): List<CharacterEntity>
}
