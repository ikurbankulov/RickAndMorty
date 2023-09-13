package com.example.presentation.screens.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.data.network.models.CharacterDto
import com.example.data.repository.RepositoryImpl
import com.example.domain.models.Character
import com.example.domain.use_cases.GetCharactersFromNetWorkUseCase
import kotlinx.coroutines.launch

class CharacterListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getCharactersFromNetWorkUseCase = GetCharactersFromNetWorkUseCase(repository)

    private val _characterList = MutableLiveData<PagingData<CharacterDto>>()
    val characterList: LiveData<PagingData<CharacterDto>> = _characterList

    init {
        loadCharacterList()
    }

    private fun loadCharacterList() {
        viewModelScope.launch {
            val characterList = getCharactersFromNetWorkUseCase.invoke()
            _characterList.value = characterList
        }
    }
}