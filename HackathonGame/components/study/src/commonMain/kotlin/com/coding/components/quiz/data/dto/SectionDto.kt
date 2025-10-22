package com.coding.components.quiz.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SectionDto(
    val id: Int,
    val items: List<ItemDto>,
    val title: String
)