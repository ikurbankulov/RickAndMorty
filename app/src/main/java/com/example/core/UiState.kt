package com.example.core

import androidx.paging.PagingData
import com.example.data.network.models.CharacterDto
import com.example.domain.models.Character


sealed class UiState{
    object Init: UiState()
    object Loading: UiState()
    data class Success(val characterList: List<PagingData<CharacterDto>>) : UiState()
    data class Error(val message: String) : UiState()
}
