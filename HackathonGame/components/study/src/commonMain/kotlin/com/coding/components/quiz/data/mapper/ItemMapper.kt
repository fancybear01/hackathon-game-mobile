package com.coding.components.quiz.data.mapper

import com.coding.components.quiz.data.dto.ItemDto
import com.coding.components.quiz.domain.model.Item

internal fun ItemDto.toDomain(): Item = Item(
    isCompleted = this.is_completed,
    isTest = this.is_test,
    itemID = this.item_id,
    sectionID = this.section_id,
    title = this.title
)