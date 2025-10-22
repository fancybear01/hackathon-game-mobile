package com.coding.course_screen_impl.course_screen.model

import com.coding.components.quiz.domain.model.Section

fun Section.toUi(): SectionUi = SectionUi(
    title = this.title,
    items = this.items.map { it.toUi() },
    status = SectionStatus.NOT_STARTED
)