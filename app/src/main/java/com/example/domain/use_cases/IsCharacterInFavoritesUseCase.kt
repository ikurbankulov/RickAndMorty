package com.example.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.domain.repository.Repository

class IsCharacterInFavoritesUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: Int) = repository.isCharacterInFavorites(id)
}