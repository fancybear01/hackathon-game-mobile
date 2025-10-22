package com.coding.components.quiz.data.repository

import com.coding.components.quiz.data.api.StudyApi
import com.coding.components.quiz.data.dto.QuestionDto
import com.coding.components.quiz.data.dto.SectionDto
import com.coding.components.quiz.data.mapper.toDomain
import com.coding.components.quiz.domain.model.Question
import com.coding.components.quiz.domain.model.Section
import com.coding.components.quiz.domain.repostory.StudyRepository

internal class StudyRepositoryImpl(
    private val studyApi: StudyApi,
) : StudyRepository {

    override suspend fun getQuestions(): Result<List<Question>> = studyApi
        .getQuestions()
        .map { list -> list.map(QuestionDto::toDomain) }

    override suspend fun getSections(): Result<List<Section>> = studyApi
        .getSections()
        .map { sections -> sections.map(SectionDto::toDomain) }
}