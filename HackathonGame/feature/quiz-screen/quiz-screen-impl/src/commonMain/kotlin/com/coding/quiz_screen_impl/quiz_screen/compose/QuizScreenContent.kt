package com.coding.quiz_screen_impl.quiz_screen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coding.quiz_screen_impl.quiz_screen.mvi.QuizScreenState
import org.jetbrains.compose.resources.painterResource
import ru.braveowlet.simple_mvi_example.core.resources.Res
import ru.braveowlet.simple_mvi_example.core.resources.arrow_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuizScreenContent(
    state: QuizScreenState,
    onBackClick: () -> Unit = {},
    onSelectAnswer: (Int) -> Unit = {},
    onNext: () -> Unit = {},
    onRestart: () -> Unit = {},
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Викторина", color = Color.White) },
                    navigationIcon = {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.arrow_2),
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF1919EF), Color(0xFFDEE1EE))
                        )
                    )
                    .padding(innerPadding)
            ) {
                QuizStep(
                    state = state,
                    onSelectAnswer = onSelectAnswer,
                    onNext = onNext,
                    onRestart = onRestart,
                )
            }
        }
    }
}

@Composable
private fun QuizStep(
    state: QuizScreenState,
    onSelectAnswer: (Int) -> Unit,
    onNext: () -> Unit,
    onRestart: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when {
            state.isFinished -> {
                Text(
                    text = "Результат: ${state.correctCount} из ${state.questions.size}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
                Button(
                    onClick = onRestart,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6088E4)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Заново",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }

            state.questions.isEmpty() -> {
                CircularProgressIndicator(color = Color.White)
                Text(
                    text = "Загрузка вопросов...",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }

            else -> {
                val current = state.questions[state.currentIndex]
                Text(
                    text = "Вопрос ${state.currentIndex + 1} из ${state.questions.size}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(Modifier.height(8.dp))

                // Карточка с вопросом
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFBDCEFA)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFFCFCFFF), RoundedCornerShape(16.dp))
                ) {
                    Text(
                        text = current.content,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1919EF),
                        modifier = Modifier.padding(
                            vertical = 16.dp,
                            horizontal = 20.dp
                        )
                    )
                }

                Spacer(Modifier.height(16.dp))

                // Варианты ответов
                current.options.forEachIndexed { index, variant ->
                    val selected = state.selectedIndex == index
                    val buttonBorderColor =
                        if (selected) Color.White else Color(0xFFCFCFFF)
                    val textColor = Color.White
                    val buttonColor = if (selected) Color(0xFF6088E4) else Color(0xFF8694E4)

                    Button(
                        onClick = { onSelectAnswer(index) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(2.dp, buttonBorderColor, RoundedCornerShape(12.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        contentPadding = PaddingValues(
                            vertical = 14.dp,
                            horizontal = 16.dp
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = variant,
                            color = textColor,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (index < current.options.lastIndex) {
                        Spacer(Modifier.height(12.dp))
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Кнопка "Далее"
                val isLast = state.currentIndex == state.questions.lastIndex
                Button(
                    onClick = onNext,
                    enabled = state.selectedIndex != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6088E4),
                        disabledContainerColor = Color(0xFFBDCEFA)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (isLast) "Завершить" else "Далее",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}