package com.coding.course_screen_impl.course_screen.mvi

import com.coding.components.quiz.domain.model.Section
import com.coding.mvi_general.MviState

internal data class CourseScreenState(
    val sections: List<Section>
) : MviState {
    companion object {
        val DEFAULT = CourseScreenState(
            sections = emptyList<Section>()
        )
    }
}