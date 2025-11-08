package com.coding.theory_screen_impl.theory_screen.mvi

import com.coding.mvi_general.MviState
import com.coding.theory_screen_impl.theory_screen.compose.TheoryArticle

data class TheoryState(
    val articles: List<TheoryArticle>
) : MviState {
    companion object {
        val DEFAULT = TheoryState(
            articles = emptyList<TheoryArticle>()
        )
    }
}