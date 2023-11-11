package com.example.data.network.source


import com.example.data.network.models.CharacterDto
import com.example.data.network.models.ResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun loadCharacters(@Query("page") page: Int): ResponseDto

    @GET("character/{id}")
    suspend fun loadCharacter(@Path("id") id: Int): Response<CharacterDto>

    @GET("character")
    suspend fun searchCharacter(@Query("name") name: String): ResponseDto

}
