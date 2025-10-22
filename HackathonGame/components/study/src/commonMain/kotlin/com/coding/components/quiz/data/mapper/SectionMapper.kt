package com.coding.components.quiz.data.mapper

import com.coding.components.quiz.data.dto.SectionDto
import com.coding.components.quiz.domain.model.Section

internal fun SectionDto.toDomain() = Section(
    id = this.id,
    items = this.items.map { it.toDomain() },
    title = this.title,
)