package com.example.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Character
import com.example.data.repository.RepositoryImpl
import com.example.domain.use_cases.GetCharacterByIdUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {
    private val repository = RepositoryImpl()
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

     fun loadCharacter(id: Int) {
        viewModelScope.launch {
            val character = getCharacterByIdUseCase.invoke(id)
            _character.value = character
        }
    }
}