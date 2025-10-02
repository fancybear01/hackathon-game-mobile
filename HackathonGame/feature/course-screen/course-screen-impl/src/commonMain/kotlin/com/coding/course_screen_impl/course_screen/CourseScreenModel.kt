package com.coding.course_screen_impl.course_screen

import com.coding.course_screen_impl.course_screen.mvi.CourseScreenAction
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenEffect
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenEvent
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenState
import com.coding.mvi_koin_voyager.MviModel

internal class CourseScreenModel(
    tag: String
) : MviModel<CourseScreenAction, CourseScreenEffect, CourseScreenEvent, CourseScreenState>(
    defaultState = CourseScreenState,
    tag = tag
) {
    override suspend fun actor(action: CourseScreenAction) {
        when (action) {
            is CourseScreenAction.ClickButtonToBack -> {
                push(CourseScreenEvent.NavigateBack)
            }
            is CourseScreenAction.ClickOnQuiz -> {
                push(CourseScreenEvent.NavigateToQuizScreen(action.quizId))
            }
            is CourseScreenAction.ClickOnTheory -> {
                push(CourseScreenEvent.NavigateToTheoryScreen)
            }
        }
    }
}