package com.coding.theory_screen_impl.theory_screen

import com.coding.components.quiz.domain.usecase.GetTheoryUseCase
import com.coding.mvi_koin_voyager.MviModel
import com.coding.theory_screen_impl.theory_screen.compose.TheoryArticle
import com.coding.theory_screen_impl.theory_screen.mvi.TheoryAction
import com.coding.theory_screen_impl.theory_screen.mvi.TheoryEffect
import com.coding.theory_screen_impl.theory_screen.mvi.TheoryEvent
import com.coding.theory_screen_impl.theory_screen.mvi.TheoryState

internal class TheoryScreenModel(
    tag: String,
    private val getTheory: GetTheoryUseCase,
    private val theoryId: Int?,
) : MviModel<TheoryAction, TheoryEffect, TheoryEvent, TheoryState>(
    defaultState = TheoryState.DEFAULT,
    tag = tag
) {
    override suspend fun bootstrap() {
        val id = theoryId ?: return
        getTheory(id)
            .onSuccess { theory ->
                push(TheoryEffect.GetTheory(theory))
            }
            .onFailure {
                // TODO: обработать ошибку (показать стейт ошибки и т.п.)
            }
    }

    override suspend fun actor(action: TheoryAction) = when (action) {
        is TheoryAction.OnArticleClick -> {
            // TODO: логика обработки клика
        }
    }

    override fun reducer(effect: TheoryEffect, previousState: TheoryState): TheoryState = when (effect) {
        is TheoryEffect.GetTheory -> previousState.copy(
            articles = effect.theory.content.mapIndexed { index, contentPart ->
                TheoryArticle(
                    id = "${effect.theory.id}-${index}",
                    title = effect.theory.title,
                    content = contentPart
                )
            }
        )
    }
}