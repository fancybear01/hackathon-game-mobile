package com.coding.course_screen_impl.course_screen.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coding.components.quiz.domain.model.Item

@Composable
fun SectionItem(
    item: Item,
    onItemClick: (Item) -> Unit
) {
    val backgroundColor = if (item.isCompleted) Color(0xFF6088E4) else Color.White
    val textColor = if (item.isCompleted) Color.White else Color(0xFF1919EF)
    Text(
        text = item.title,
        color = textColor,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = HeaderStartInset, end = ScreenHorizontalPadding, top = 4.dp, bottom = 4.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable { onItemClick(item) }
            .padding(10.dp)
    )
}