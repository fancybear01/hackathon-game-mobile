package com.coding.components.pet.data.api

import com.coding.components.pet.data.dto.PetDto
import io.ktor.client.HttpClient

internal interface PetApi {
    suspend fun getPet(): Result<PetDto>
}

internal class PetApiImpl(
    private val httpClient: HttpClient
) : PetApi {
    override suspend fun getPet(): Result<PetDto> {
        // httpClient.fetchForGet("https://example.com/api/pet")
        return Result.success(
            PetDto(
                id = 1,
                name = "Газпромик",
                age = 77,
                exp = 777
            )
        )
    }
}