package com.coding.course_screen_impl.course_screen.mvi

import com.coding.mvi_general.MviEvent

internal sealed interface CourseScreenEvent : MviEvent {
    data object NavigateBack : CourseScreenEvent
    data class NavigateToQuizScreen(val quizId: Int) : CourseScreenEvent
    data object NavigateToTheoryScreen : CourseScreenEvent
}