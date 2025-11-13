package com.coding.components.quiz.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TheoryDto(
    val id: Int,
    val title: String,
    val content: List<String>
)