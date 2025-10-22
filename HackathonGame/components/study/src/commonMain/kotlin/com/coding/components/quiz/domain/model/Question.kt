package com.coding.components.quiz.domain.model

data class Question(
    val question: String,
    val variants: List<String>,
    val answer: String
)