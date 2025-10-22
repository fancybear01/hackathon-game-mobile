package com.coding.components.quiz.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    val question: String,
    val variants: List<String>,
    val answer: String
)