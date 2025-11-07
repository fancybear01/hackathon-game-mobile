package com.coding.components.pet.domain.usecase

import com.coding.components.pet.domain.model.Pet
import com.coding.components.pet.domain.repository.PetRepository

interface GetPetUseCase {
    suspend operator fun invoke(): Result<Pet>
}

internal class GetPetUseCaseImpl(
    val petRepository: PetRepository
): GetPetUseCase {
    override suspend fun invoke(): Result<Pet> {
        return petRepository.getPet()
    }
}