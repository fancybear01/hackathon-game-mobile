package com.coding.components.pet.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SetNameDto(
    val name: String,
    val userID: Int
)