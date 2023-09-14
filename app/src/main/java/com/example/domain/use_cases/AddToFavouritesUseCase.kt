package com.example.domain.use_cases

import com.example.domain.models.Character
import com.example.domain.repository.Repository
import javax.inject.Inject

class AddToFavouritesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(character: Character) = repository.addToFavourites(character)
}