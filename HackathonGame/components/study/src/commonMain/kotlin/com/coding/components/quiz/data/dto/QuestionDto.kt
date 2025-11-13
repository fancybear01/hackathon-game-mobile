package com.coding.components.quiz.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    val id: Int,
    val title: String,
    val content: String,
    val options: List<String>,
    val correctAnswer: String
)