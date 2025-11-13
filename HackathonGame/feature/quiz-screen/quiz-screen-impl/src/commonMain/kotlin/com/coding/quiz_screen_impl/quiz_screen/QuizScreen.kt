package com.coding.quiz_screen_impl.quiz_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.coding.mvi_koin_voyager.MviView
import com.coding.mvi_koin_voyager.collectEvent
import com.coding.quiz_screen_impl.quiz_screen.compose.QuizScreenContent
import com.coding.quiz_screen_impl.quiz_screen.mvi.QuizScreenAction
import com.coding.quiz_screen_impl.quiz_screen.mvi.QuizScreenEvent
import com.coding.quiz_screen_impl.quiz_screen.mvi.QuizScreenState
import kotlinx.coroutines.flow.Flow

internal class QuizScreen(
    private val quizId: Int
) : MviView<QuizScreenAction, QuizScreenEvent, QuizScreenState>, MviView.ParamsProvider {
    override fun modelParams(): Array<Any> = arrayOf(quizId)

    @Composable
    override fun content(
        state: QuizScreenState,
        eventFlow: Flow<QuizScreenEvent>,
        pushAction: (QuizScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow

        eventFlow.collectEvent { event ->
            when(event) {
                QuizScreenEvent.NavigateToBack ->
                    navigator.pop()
            }
        }

        QuizScreenContent(
            state = state,
            onBackClick = { pushAction(QuizScreenAction.ClickButtonToBack) },
            onSelectAnswer = { index: Int -> pushAction(QuizScreenAction.SelectAnswer(index)) },
            onNext = { pushAction(QuizScreenAction.ClickNext) },
            onRestart = { pushAction(QuizScreenAction.ClickRestart) }
        )
    }
}