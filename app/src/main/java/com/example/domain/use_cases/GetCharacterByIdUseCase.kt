package com.example.domain.use_cases

import com.example.domain.repository.Repository

class GetCharacterByIdUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: Int) = repository.getCharacterById(id)
}