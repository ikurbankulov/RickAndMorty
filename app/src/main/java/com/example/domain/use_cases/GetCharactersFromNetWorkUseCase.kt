package com.example.domain.use_cases

import com.example.domain.repository.Repository
import javax.inject.Inject

class GetCharactersFromNetWorkUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.getCharactersFromNetWork()
}