package com.coding.course_screen_impl.course_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.coding.course_screen_impl.course_screen.compose.CourseScreenContent
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenAction
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenEvent
import com.coding.course_screen_impl.course_screen.mvi.CourseScreenState
import com.coding.mvi_koin_voyager.MviView
import com.coding.mvi_koin_voyager.collectEvent
import com.coding.quiz_screen_api.QuizScreenApi
import com.coding.theory_screen_api.TheoryScreenApi
import kotlinx.coroutines.flow.Flow
import org.koin.compose.koinInject

internal class CourseScreen : MviView<CourseScreenAction, CourseScreenEvent, CourseScreenState> {

    @Composable
    override fun content(
        state: CourseScreenState,
        eventFlow: Flow<CourseScreenEvent>,
        pushAction: (CourseScreenAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val quizScreenApi = koinInject<QuizScreenApi>()
        val theoryScreenApi = koinInject<TheoryScreenApi>()

        eventFlow.collectEvent { event ->
            when(event) {
                CourseScreenEvent.NavigateBack ->
                    navigator.pop()
                is CourseScreenEvent.NavigateToQuizScreen ->
                    navigator.push(quizScreenApi.quizScreen(event.quizId))
                is CourseScreenEvent.NavigateToTheoryScreen ->
                    navigator.push(theoryScreenApi.theoryScreen(event.theoryId))
            }
        }

        CourseScreenContent(
            onClickBack = {
                pushAction(CourseScreenAction.ClickButtonToBack)
            },
            sections = state.sections,
            onNavigateToQuiz = { quizId ->
                pushAction(CourseScreenAction.ClickOnQuiz(quizId))
            },
            onNavigateToTheory = { theoryId ->
                pushAction(CourseScreenAction.ClickOnTheory(theoryId))
            }
        )
    }
}