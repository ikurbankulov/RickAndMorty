package com.example.domain.use_cases

import com.example.domain.repository.Repository
import javax.inject.Inject

class SearchCharacterFromNetWorkUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(name: String) = repository.searchCharacterFromNetWork(name)
}