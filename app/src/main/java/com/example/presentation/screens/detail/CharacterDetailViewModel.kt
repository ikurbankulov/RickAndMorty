package com.example.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Character
import com.example.domain.use_cases.AddToFavouritesUseCase
import com.example.domain.use_cases.GetCharacterByIdFromNetWorkUseCase
import com.example.domain.use_cases.IsCharacterInFavoritesUseCase
import com.example.domain.use_cases.RemoveFromFavouritesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdFromNetWorkUseCase: GetCharacterByIdFromNetWorkUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase,
    private val isCharacterInFavoritesUseCase: IsCharacterInFavoritesUseCase
) : ViewModel() {


    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            val character = getCharacterByIdFromNetWorkUseCase(id)
            _character.value = character
        }
    }

    fun addToFavourites(character: Character) {
        viewModelScope.launch {
            addToFavouritesUseCase(character)
        }
    }

    fun removeFromFavourites(character: Character) {
        viewModelScope.launch {
            removeFromFavouritesUseCase(character.id)
        }
    }

    fun isCharacterInFavorites(id: Int): LiveData<Boolean> {
        return isCharacterInFavoritesUseCase(id)
    }

}