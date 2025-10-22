package com.coding.course_screen_impl.course_screen.model

import com.coding.components.quiz.domain.model.Item

fun Item.toUi(): ItemUi = ItemUi(
    id = this.itemID,
    title = this.title,
    isTest = this.isTest
)