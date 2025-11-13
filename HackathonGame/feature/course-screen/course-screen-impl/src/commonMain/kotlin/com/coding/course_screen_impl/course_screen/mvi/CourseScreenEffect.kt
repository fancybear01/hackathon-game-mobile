package com.coding.course_screen_impl.course_screen.mvi

import com.coding.components.quiz.domain.model.Section
import com.coding.mvi_general.MviEffect

internal sealed interface CourseScreenEffect : MviEffect {
    data class GetSections(val sections: List<Section>) : CourseScreenEffect
}