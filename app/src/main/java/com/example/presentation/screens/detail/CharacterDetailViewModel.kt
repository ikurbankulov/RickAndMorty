package com.example.presentation.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.local.mapper.Mapper
import com.example.data.repository.RepositoryImpl
import com.example.domain.models.Character
import com.example.domain.use_cases.AddToFavouritesUseCase
import com.example.domain.use_cases.GetCharacterByIdFromNetWorkUseCase
import com.example.domain.use_cases.RemoveFromFavouritesUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val getCharacterByIdFromNetWorkUseCase = GetCharacterByIdFromNetWorkUseCase(repository)
    private val addToFavouritesUseCase = AddToFavouritesUseCase(repository)
    private val removeFromFavouritesUseCase = RemoveFromFavouritesUseCase(repository)


    private val mapper = Mapper()

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
        return repository.isCharacterInFavorites(id)
    }

}