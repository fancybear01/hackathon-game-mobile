package com.coding.components.quiz.domain.repostory

import com.coding.components.quiz.domain.model.Question
import com.coding.components.quiz.domain.model.Section

interface StudyRepository {
    suspend fun getQuestions(): Result<List<Question>>
    suspend fun getSections(): Result<List<Section>>
}