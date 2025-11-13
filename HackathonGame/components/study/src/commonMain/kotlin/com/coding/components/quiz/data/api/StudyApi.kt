package com.coding.components.quiz.data.api

import com.coding.components.quiz.data.dto.QuestionDto
import com.coding.components.quiz.data.dto.SectionDto
import com.coding.components.quiz.data.dto.TheoryDto
import com.coding.core.network.Constants
import com.coding.core.network.fetchForGet
import io.ktor.client.HttpClient

internal interface StudyApi {
    suspend fun getQuestions(id: Int): Result<List<QuestionDto>>
    suspend fun getSections(): Result<List<SectionDto>>
    suspend fun getTheory(id: Int): Result<TheoryDto>
}

val BASE_URL = Constants.BASE_URL
val USER_ID = Constants.USER_ID

internal class StudyApiImpl(
    private val httpClient: HttpClient
) : StudyApi {

    override suspend fun getQuestions(id: Int): Result<List<QuestionDto>> {
        return httpClient.fetchForGet("$BASE_URL/quiz/$id")
    }


    override suspend fun getSections(): Result<List<SectionDto>> {
        return httpClient.fetchForGet("$BASE_URL/sectionsStatuses?user_id=$USER_ID")
    }

    override suspend fun getTheory(id: Int): Result<TheoryDto> {
        return httpClient.fetchForGet("$BASE_URL/theory/$id")
    }
}