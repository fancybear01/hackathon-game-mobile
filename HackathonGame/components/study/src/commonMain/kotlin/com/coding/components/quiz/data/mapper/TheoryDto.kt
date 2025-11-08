package com.coding.components.quiz.data.mapper

import com.coding.components.quiz.data.dto.TheoryDto
import com.coding.components.quiz.domain.model.Theory

internal fun TheoryDto.toDomain(): Theory = Theory(
    id = this.id,
    title = this.title,
    content = this.content,
)