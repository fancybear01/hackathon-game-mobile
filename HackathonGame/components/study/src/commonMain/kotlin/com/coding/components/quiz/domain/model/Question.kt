package com.coding.components.quiz.domain.model

data class Question(
    val id: Int,
    val title: String,
    val content: String,
    val options: List<String>,
    val correctAnswer: String
)