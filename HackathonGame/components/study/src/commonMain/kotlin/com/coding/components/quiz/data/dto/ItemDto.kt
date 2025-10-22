package com.coding.components.quiz.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    val isTest: Boolean,
    val itemID: Int,
    val sectionID: Int,
    val title: String
)