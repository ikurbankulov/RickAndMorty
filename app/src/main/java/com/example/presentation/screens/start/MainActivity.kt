package com.example.presentation.screens.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.data.network.ApiFactory
import com.example.data.network.ApiService
import com.example.rickandmorty.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val apiService: ApiService = ApiFactory.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Выполняем запрос к API
        GlobalScope.launch(Dispatchers.IO) {
           val response = apiService.loadCharacters()
            Log.d("response", response.toString())
        }
    }
}