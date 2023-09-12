package com.example.domain.use_cases

import com.example.domain.repository.Repository

class GetCharactersFromDatabaseUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getCharactersFromDatabase()
}