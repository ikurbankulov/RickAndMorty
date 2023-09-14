package com.example.domain.use_cases

import com.example.domain.repository.Repository
import javax.inject.Inject

class GetCharacterByIdFromNetWorkUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Int) = repository.getCharacterByIdFromNetWork(id)
}