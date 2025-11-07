package com.coding.course_screen_impl.course_screen.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coding.components.quiz.domain.model.Item
import com.coding.components.quiz.domain.model.Section
import com.coding.course_screen_impl.course_screen.model.SectionStatus

@Composable
fun SectionGroup(section: Section, onItemClick: (Item) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        var counter = 0
        for (item in section.items) {
            if (item.isCompleted) counter++
        }
        val status = when {
            (counter == section.items.size) -> SectionStatus.COMPLETED
            counter >= 1 -> SectionStatus.IN_PROGRESS
            else -> SectionStatus.NOT_STARTED
        }
        SectionHeader(title = section.title, status = status)

        section.items.forEach { item ->
            SectionItem(item = item, onItemClick = onItemClick)
        }
    }
}