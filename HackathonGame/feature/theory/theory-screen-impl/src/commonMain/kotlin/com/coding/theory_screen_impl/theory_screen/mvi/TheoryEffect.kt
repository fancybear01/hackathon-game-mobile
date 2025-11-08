package com.coding.theory_screen_impl.theory_screen.mvi

import com.coding.components.quiz.domain.model.Theory
import com.coding.mvi_general.MviEffect

internal sealed interface TheoryEffect : MviEffect {
    data class GetTheory(val theory: Theory) : TheoryEffect
}