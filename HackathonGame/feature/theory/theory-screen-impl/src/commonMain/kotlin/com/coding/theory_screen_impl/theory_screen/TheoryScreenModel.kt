package com.coding.theory_screen_impl.theory_screen

import com.coding.mvi_koin_voyager.MviModel
import com.coding.theory_screen_impl.theory_screen.compose.TheoryArticle
import com.coding.theory_screen_impl.theory_screen.compose.sampleArticles
import com.coding.theory_screen_impl.theory_screen.mvi.TheoryAction
import com.coding.theory_screen_impl.theory_screen.mvi.TheoryEffect
import com.coding.theory_screen_impl.theory_screen.mvi.TheoryEvent
import com.coding.theory_screen_impl.theory_screen.mvi.TheoryState
import kotlinx.coroutines.delay

internal class TheoryScreenModel(
    tag: String,
) : MviModel<TheoryAction, TheoryEffect, TheoryEvent, TheoryState>(
    defaultState = TheoryState(
        articles = sampleArticles
    ),
    tag = tag
) {

    override suspend fun actor(action: TheoryAction) = when (action) {
        // Здесь можно обработать клики или другие действия
        is TheoryAction.OnArticleClick -> {
            // Просто TODO или логика, если нужно
        }
    }
}