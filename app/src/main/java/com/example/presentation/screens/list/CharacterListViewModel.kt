package com.example.presentation.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RepositoryImpl
import com.example.domain.models.Character
import com.example.domain.use_cases.GetCharacterListUseCase
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    private val repository = RepositoryImpl()
    private val getCharacterListUseCase = GetCharacterListUseCase(repository)

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>> = _characterList

    init {
        loadCharacterList()
    }

    private fun loadCharacterList() {
        viewModelScope.launch {
            val characterList = getCharacterListUseCase.invoke()
            _characterList.value = characterList
        }
    }
}