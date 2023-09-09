package com.example.domain.use_cases

import com.example.domain.repository.Repository

class SearchCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke(name: String) = repository.searchCharacter(name)
}