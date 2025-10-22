package com.coding.components.quiz.data.api

import com.coding.components.quiz.data.dto.QuestionDto
import io.ktor.client.HttpClient

internal interface StudyApi {
    suspend fun getQuestions(): Result<List<QuestionDto>>
}

internal class StudyApiImpl(
    private val httpClient: HttpClient
) : StudyApi {

    override suspend fun getQuestions(): Result<List<QuestionDto>> =
        // httpClient.fetchForGet("https://example.com/api/quiz")
        Result.success(
            listOf(
                QuestionDto(
                    question = "Что такое Kotlin Multiplatform?",
                    variants = listOf(
                        "Фреймворк UI для Android",
                        "Язык для серверной разработки",
                        "Технология для шаринга кода между платформами",
                        "IDE от JetBrains"
                    ),
                    answer = "Технология для шаринга кода между платформами"
                ),
                QuestionDto(
                    question = "Какой клиент используется здесь для сети?",
                    variants = listOf("Retrofit", "Volley", "Ktor", "OkHttp только"),
                    answer = "Ktor"
                ),
                QuestionDto(
                    question = "Как обновляется состояние в MVI-модели?",
                    variants = listOf(
                        "Напрямую менять поля state",
                        "Через эффекты и редьюсер",
                        "Через глобальные переменные",
                        "Через BroadcastReceiver"
                    ),
                    answer = "Через эффекты и редьюсер"
                )
            )
        )
}