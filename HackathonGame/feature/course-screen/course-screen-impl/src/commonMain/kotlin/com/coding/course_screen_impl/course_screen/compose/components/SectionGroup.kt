package com.coding.course_screen_impl.course_screen.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coding.course_screen_impl.course_screen.model.ItemUi
import com.coding.course_screen_impl.course_screen.model.SectionUi

@Composable
fun SectionGroup(section: SectionUi, onItemClick: (ItemUi) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SectionHeader(title = section.title, status = section.status)

        section.items.forEach { item ->
            SectionItem(item = item, onItemClick = onItemClick)
        }
    }
}