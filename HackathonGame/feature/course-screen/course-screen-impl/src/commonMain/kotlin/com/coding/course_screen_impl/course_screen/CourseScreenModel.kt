package com.coding.course_screen_impl.course_screen

import com.coding.components.quiz.domain.usecase.GetSectionsUseCase
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenAction
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenEffect
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenEvent
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenState
import com.coding.mvi_koin_voyager.MviModel

internal class CourseScreenModel(
    tag: String,
    private val getSections: GetSectionsUseCase
) : MviModel<CourseScreenAction, CourseScreenEffect, CourseScreenEvent, CourseScreenState>(
    defaultState = CourseScreenState.DEFAULT,
    tag = tag
) {
    override suspend fun bootstrap() {
        getSections()
            .onSuccess { list -> push(CourseScreenEffect.GetSections(list)) }
            .onFailure { /* TODO: обработать ошибку */ }
    }
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

    override fun reducer(
        effect: CourseScreenEffect,
        previousState: CourseScreenState
    ): CourseScreenState =
        when (effect) {
            is CourseScreenEffect.GetSections -> previousState.copy(
                sections = effect.sections
            )
        }
}