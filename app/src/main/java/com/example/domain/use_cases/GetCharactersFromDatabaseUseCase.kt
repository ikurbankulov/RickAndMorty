package com.example.domain.use_cases

import com.example.domain.repository.Repository
import javax.inject.Inject

class GetCharactersFromDatabaseUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.getCharactersFromDatabase()
}