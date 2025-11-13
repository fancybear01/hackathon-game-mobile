package com.coding.components.quiz.domain.repostory

import com.coding.components.quiz.domain.model.Question
import com.coding.components.quiz.domain.model.Section
import com.coding.components.quiz.domain.model.Theory

interface StudyRepository {
    suspend fun getQuestions(id: Int): Result<List<Question>>
    suspend fun getSections(): Result<List<Section>>
    suspend fun getTheory(id: Int): Result<Theory>
}