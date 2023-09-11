package com.example.presentation.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.database.DataBase
import com.example.data.local.mapper.Mapper
import com.example.data.local.models.CharacterDbModel
import com.example.data.repository.RepositoryImpl
import com.example.domain.models.Character
import com.example.domain.use_cases.GetCharacterByIdUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl()
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    private val db = DataBase.getInstance(application).characterDao()
    private val mapper = Mapper()

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            val character = getCharacterByIdUseCase.invoke(id)
            _character.value = character
        }
    }

    fun addToFavourites(character: Character) {
        viewModelScope.launch {
            db.insertCharacter(mapper.mapToDbModel(character))
        }
    }

    fun removeFromFavourites(character: Character) {
        viewModelScope.launch {
            db.deleteCharacter(character.id)
        }
    }

    fun isCharacterInFavorites(id: Int): LiveData<Boolean> {
        return db.isCharacterInFavorites(id)
    }

}