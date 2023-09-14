package com.example.core

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.domain.models.Character


sealed class State{
    object Loading: State()
    data class Success(val characterList: LiveData<PagingData<Character>>) : State()
    data class Error(val message: String) : State()
}
