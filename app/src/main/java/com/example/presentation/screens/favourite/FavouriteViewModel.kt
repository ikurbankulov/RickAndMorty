package com.example.presentation.screens.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Character
import com.example.domain.use_cases.GetCharactersFromDatabaseUseCase
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val getCharactersFromDb: GetCharactersFromDatabaseUseCase
) : ViewModel() {

    val characterList: LiveData<List<Character>>
        get() = getCharactersFromDb.invoke()

}
