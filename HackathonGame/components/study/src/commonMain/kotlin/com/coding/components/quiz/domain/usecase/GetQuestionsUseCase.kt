package com.coding.components.quiz.domain.usecase

import com.coding.components.quiz.domain.model.Question
import com.coding.components.quiz.domain.repostory.StudyRepository

interface GetQuestionsUseCase {
    suspend operator fun invoke(id: Int): Result<List<Question>>
}

internal class GetQuestionsUseCaseImpl(
    private val quizRepository: StudyRepository
) : GetQuestionsUseCase {

    override suspend fun invoke(id: Int): Result<List<Question>> =
        quizRepository.getQuestions(id = id)
}