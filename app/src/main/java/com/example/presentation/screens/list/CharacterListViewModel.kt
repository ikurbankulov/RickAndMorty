package com.example.presentation.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.domain.models.Character
import com.example.domain.use_cases.GetCharactersFromNetWorkUseCase
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val getCharactersFromNetWorkUseCase: GetCharactersFromNetWorkUseCase
) : ViewModel() {

    val characterList: LiveData<PagingData<Character>>
        get() = getCharactersFromNetWorkUseCase.invoke()

}

