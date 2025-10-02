package com.coding.course_screen_impl.course_screen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.coding.course_screen_impl.course_screen.compose.components.SectionGroup
import com.coding.course_screen_impl.course_screen.compose.components.TopBar
import com.coding.course_screen_impl.course_screen.model.Section

@Composable
fun CourseScreenContent(
    sections: List<Section>,
    onClickBack: () -> Unit,
    onNavigateToQuiz: (Int) -> Unit,
    onNavigateToTheory: (Int) -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopBar(
                    onClickBack = onClickBack
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient( // Создаем линейный градиент
                            colors = listOf(Color(0xFF1919EF), Color(0xFF8694E4)),
                        )
                    )
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                sections.forEach { section ->
                    SectionGroup(
                        section = section,
                        onItemClick = { item ->
                            if (item.isTest) {
                                onNavigateToQuiz(item.id)
                            } else {
                                onNavigateToTheory(item.id)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}