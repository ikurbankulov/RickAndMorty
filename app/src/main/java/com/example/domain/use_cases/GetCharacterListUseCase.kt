package com.example.domain.use_cases

import com.example.domain.repository.Repository

class GetCharacterListUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getCharacterList()
}