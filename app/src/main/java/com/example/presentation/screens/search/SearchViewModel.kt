package com.example.presentation.screens.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RepositoryImpl
import com.example.domain.models.Character
import com.example.domain.use_cases.SearchCharacterFromNetWorkUseCase
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val searchCharactersFromNetWorkUseCase = SearchCharacterFromNetWorkUseCase(repository)

    private val _searchList = MutableLiveData<List<Character>>()
    val searchList: LiveData<List<Character>> = _searchList


    fun searchCharacters(query: String) {
        viewModelScope.launch {
            val searchResults = searchCharactersFromNetWorkUseCase.invoke(query)
            _searchList.postValue(searchResults)
        }
    }


}