package com.coding.components.pet.domain.repository

import com.coding.components.pet.domain.model.Pet

interface PetRepository {
    suspend fun getPet(): Result<Pet>
    suspend fun setPetName(name: String, userID: Int)
}