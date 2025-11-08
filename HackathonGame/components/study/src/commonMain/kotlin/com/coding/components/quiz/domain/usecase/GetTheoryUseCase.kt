package com.coding.components.quiz.domain.usecase

import com.coding.components.quiz.domain.model.Theory
import com.coding.components.quiz.domain.repostory.StudyRepository

interface GetTheoryUseCase {
    suspend operator fun invoke(id: Int): Result<Theory>
}

internal class GetTheoryUseCaseImpl(
    private val studyRepository: StudyRepository
) : GetTheoryUseCase {
    override suspend fun invoke(id: Int): Result<Theory> = studyRepository
        .getTheory(id = id)
}