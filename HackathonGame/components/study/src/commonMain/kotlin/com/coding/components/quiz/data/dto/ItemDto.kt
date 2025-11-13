package com.coding.components.quiz.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    val is_completed: Boolean,
    val is_test: Boolean,
    val item_id: Int,
    val section_id: Int,
    val title: String
)