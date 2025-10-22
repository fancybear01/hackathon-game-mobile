package com.coding.components.quiz.data.mapper

import com.coding.components.quiz.data.dto.QuestionDto
import com.coding.components.quiz.domain.model.Question

internal fun QuestionDto.toDomain(): Question = Question(
    question = this.question,
    variants = this.variants,
    answer = this.answer
)