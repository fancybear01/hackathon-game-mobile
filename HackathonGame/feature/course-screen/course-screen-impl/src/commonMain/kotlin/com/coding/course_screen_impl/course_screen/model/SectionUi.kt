package com.coding.course_screen_impl.course_screen.model

data class SectionUi(
    val title: String,
    val items: List<ItemUi>,
    val status: SectionStatus
)
