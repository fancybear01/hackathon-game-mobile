package com.coding.components.quiz.domain.repostory

import com.coding.components.quiz.domain.model.Question

interface StudyRepository {
    suspend fun getQuestions(): Result<List<Question>>
}