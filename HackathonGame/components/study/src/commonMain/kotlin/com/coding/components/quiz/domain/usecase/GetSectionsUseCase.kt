package com.coding.components.quiz.domain.usecase

import com.coding.components.quiz.domain.model.Section
import com.coding.components.quiz.domain.repostory.StudyRepository

interface GetSectionsUseCase {
    suspend operator fun invoke(): Result<List<Section>>
}

internal class GetSectionsUseCaseImpl(
    private val studyRepository: StudyRepository
) : GetSectionsUseCase {

    override suspend fun invoke(): Result<List<Section>> =
        studyRepository.getSections()
}