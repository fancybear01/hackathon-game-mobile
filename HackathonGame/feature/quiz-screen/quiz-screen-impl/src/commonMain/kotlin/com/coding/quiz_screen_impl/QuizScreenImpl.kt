package com.coding.quiz_screen_impl

import cafe.adriel.voyager.core.screen.Screen
import com.coding.quiz_screen_api.QuizScreenApi
import com.coding.quiz_screen_impl.quiz_screen.QuizScreen

internal class QuizScreenImpl : QuizScreenApi {
    override fun quizScreen(id: Int): Screen = QuizScreen(id)
}