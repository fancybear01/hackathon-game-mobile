package com.coding.components.quiz.data.mapper

import com.coding.components.quiz.data.dto.QuestionDto
import com.coding.components.quiz.domain.model.Question

internal fun QuestionDto.toDomain(): Question = Question(
    id = this.id,
    title = this.title,
    content = this.content,
    options = this.options,
    correctAnswer = this.correctAnswer
)