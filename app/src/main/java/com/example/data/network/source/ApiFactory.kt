package com.example.data.network.source

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        private val interceptor = ChuckerInterceptor(Application())

        private val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        private val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        val apiService: ApiService = retrofit.create(ApiService::class.java)
}
