package com.coding.components.pet.domain.usecase

import com.coding.components.pet.domain.model.Pet
import com.coding.components.pet.domain.repository.PetRepository

interface SetNameUseCase {
    suspend operator fun invoke(name: String, userID: Int)
}

internal class SetNameUseCaseImpl(
    val petRepository: PetRepository
): SetNameUseCase {
    override suspend fun invoke(name: String, userID: Int) {
        return petRepository.setPetName(name = name, userID = userID)
    }
}