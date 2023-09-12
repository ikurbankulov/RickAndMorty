package com.example.domain.use_cases

import com.example.domain.repository.Repository

class RemoveFromFavouritesUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: Int) = repository.removeFromFavourites(id)
}