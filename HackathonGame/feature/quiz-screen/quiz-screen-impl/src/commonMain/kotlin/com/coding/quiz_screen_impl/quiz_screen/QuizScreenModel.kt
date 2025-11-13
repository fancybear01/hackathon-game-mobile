package com.coding.quiz_screen_impl.quiz_screen

import com.coding.components.quiz.domain.usecase.GetQuestionsUseCase
import com.coding.mvi_koin_voyager.MviModel
import com.coding.quiz_screen_impl.quiz_screen.mvi.QuizScreenAction
import com.coding.quiz_screen_impl.quiz_screen.mvi.QuizScreenEffect
import com.coding.quiz_screen_impl.quiz_screen.mvi.QuizScreenEvent
import com.coding.quiz_screen_impl.quiz_screen.mvi.QuizScreenState

internal class QuizScreenModel(
    tag: String,
    private val getQuestions: GetQuestionsUseCase,
    private val quizId: Int?
) : MviModel<QuizScreenAction, QuizScreenEffect, QuizScreenEvent, QuizScreenState>(
    defaultState = QuizScreenState.DEFAULT,
    tag = tag
) {

    override suspend fun bootstrap() {
        val id = quizId ?: return
        getQuestions(id)
            .onSuccess { list ->
                push(QuizScreenEffect.SetQuestions(list))
            }
            .onFailure {
                println("getQuestions $it")
                /* TODO: обработать ошибку */
            }
    }

    override suspend fun actor(action: QuizScreenAction) {
        when (action) {
            is QuizScreenAction.ClickButtonToBack -> {
                push(QuizScreenEvent.NavigateToBack)
            }
            is QuizScreenAction.SelectAnswer -> {
                // Сохраняем выбранный индекс
                push(QuizScreenEffect.AnswerSelected(action.index))
            }
            QuizScreenAction.ClickNext -> {
                val state = stateFlow.value
                if (state.isFinished) return
                val current = state.questions.getOrNull(state.currentIndex) ?: return
                val selected = state.selectedIndex ?: return
                val isCorrect = current.options.getOrNull(selected) == current.correctAnswer
                val newCorrect = state.correctCount + if (isCorrect) 1 else 0
                val nextIndex = state.currentIndex + 1
                val finished = nextIndex >= state.questions.size
                push(QuizScreenEffect.NextStep(nextIndex = nextIndex, correctCount = newCorrect, isFinished = finished))
            }
            QuizScreenAction.ClickRestart -> {
                push(QuizScreenEffect.ResetQuiz)
            }
        }
    }

    override fun reducer(effect: QuizScreenEffect, previousState: QuizScreenState): QuizScreenState =
        when (effect) {
            is QuizScreenEffect.SetQuestions -> previousState.copy(
                questions = effect.questions,
                currentIndex = 0,
                selectedIndex = null,
                correctCount = 0,
                isFinished = effect.questions.isEmpty()
            )
            is QuizScreenEffect.AnswerSelected -> previousState.copy(selectedIndex = effect.selectedIndex)
            is QuizScreenEffect.NextStep -> previousState.copy(
                currentIndex = if (effect.isFinished) previousState.currentIndex else effect.nextIndex,
                selectedIndex = null,
                correctCount = effect.correctCount,
                isFinished = effect.isFinished
            )
            QuizScreenEffect.ResetQuiz -> previousState.copy(
                currentIndex = 0,
                selectedIndex = null,
                correctCount = 0,
                isFinished = previousState.questions.isEmpty()
            )
        }
}