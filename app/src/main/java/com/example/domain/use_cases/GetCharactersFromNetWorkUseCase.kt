package com.example.domain.use_cases

import com.example.domain.repository.Repository

class GetCharactersFromNetWorkUseCase(private val repository: Repository) {
     operator fun invoke() = repository.getCharactersFromNetWork()
}