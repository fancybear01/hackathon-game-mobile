package com.coding.components.quiz.domain.model

data class Item(
    val isTest: Boolean,
    val itemID: Int,
    val sectionID: Int,
    val title: String
)