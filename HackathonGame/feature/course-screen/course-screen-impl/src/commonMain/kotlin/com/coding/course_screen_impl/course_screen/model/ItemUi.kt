package com.coding.course_screen_impl.course_screen.model

data class ItemUi(
    val id: Int,
    val title: String,
    val isTest: Boolean,
    val isCompleted: Boolean = false
)