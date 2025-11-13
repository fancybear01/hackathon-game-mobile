package com.coding.components.quiz.domain.model

data class Section(
    val id: Int,
    val items: List<Item>,
    val title: String
)