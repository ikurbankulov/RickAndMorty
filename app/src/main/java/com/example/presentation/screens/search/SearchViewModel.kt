package com.example.presentation.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Character
import com.example.domain.use_cases.SearchCharacterFromNetWorkUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchCharactersFromNetWorkUseCase: SearchCharacterFromNetWorkUseCase
) : ViewModel() {

    private val _searchList = MutableLiveData<List<Character>>()
    val searchList: LiveData<List<Character>> = _searchList


    fun searchCharacters(query: String) {
        viewModelScope.launch {
            val searchResults = searchCharactersFromNetWorkUseCase.invoke(query)
            _searchList.postValue(searchResults)
        }
    }
}