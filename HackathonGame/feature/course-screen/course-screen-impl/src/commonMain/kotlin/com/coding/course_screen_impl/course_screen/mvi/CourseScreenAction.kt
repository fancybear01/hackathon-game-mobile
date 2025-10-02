package com.coding.course_screen_impl.course_screen.mvi

import com.coding.mvi_general.MviAction

internal sealed interface CourseScreenAction : MviAction {
    data object ClickButtonToBack : CourseScreenAction
    data class ClickOnQuiz(val quizId: Int) : CourseScreenAction
    data class ClickOnTheory(val theoryId: Int) : CourseScreenAction
}