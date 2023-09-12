package com.example.presentation.screens.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RepositoryImpl
import com.example.domain.models.Character
import com.example.domain.use_cases.GetCharactersFromNetWorkUseCase
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getCharactersFromNetWorkUseCase = GetCharactersFromNetWorkUseCase(repository)

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>> = _characterList

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