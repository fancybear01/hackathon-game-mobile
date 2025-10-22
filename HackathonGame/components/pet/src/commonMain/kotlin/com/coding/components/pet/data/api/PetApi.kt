package com.coding.components.pet.data.api

import com.coding.components.pet.data.dto.PetDto
import com.coding.components.pet.data.dto.SetNameDto
import com.coding.core.network.fetchForGet
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal interface PetApi {
    suspend fun getPet(id: Int): Result<PetDto>
    suspend fun setPetName(request: SetNameDto)
}

val BASE_URL = "http://158.160.205.249:8080"

internal class PetApiImpl(
    private val httpClient: HttpClient
) : PetApi {
    override suspend fun getPet(id: Int): Result<PetDto> {
        return httpClient.fetchForGet("$BASE_URL/pet/$id")
    }

    override suspend fun setPetName(request: SetNameDto) {
        httpClient.post("$BASE_URL/pet/name") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}