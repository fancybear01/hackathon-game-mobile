package com.coding.components.quiz.data.mapper

import com.coding.components.quiz.data.dto.ItemDto
import com.coding.components.quiz.domain.model.Item

internal fun ItemDto.toDomain(): Item = Item(
    isTest = this.isTest,
    itemID = this.itemID,
    sectionID = this.sectionID,
    title = this.title
)