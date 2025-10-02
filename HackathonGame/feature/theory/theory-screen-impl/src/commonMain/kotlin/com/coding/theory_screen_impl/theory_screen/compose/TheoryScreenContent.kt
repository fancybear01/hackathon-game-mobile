package com.coding.theory_screen_impl.theory_screen.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import ru.braveowlet.simple_mvi_example.core.resources.Res
import ru.braveowlet.simple_mvi_example.core.resources.arrow_2
import kotlin.math.abs
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheoryScreenContent(
    articles: List<TheoryArticle>,
    initialIndex: Int = 0,
    onBackClick: () -> Unit = {},
) {
    val pagerState = rememberPagerState(initialPage = initialIndex, pageCount = { articles.size })

    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Теория", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                painter = painterResource(Res.drawable.arrow_2),
                                contentDescription = "Back",
                                tint = Color.White,
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
                    .background(Color.Black)
                    .padding(innerPadding)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    val article = articles[page]

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Заглушка для картинки
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.DarkGray)
                        )

                        // Затемнённый фон
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.4f))
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp)
                                .padding(bottom = 48.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            var isTitleVisible by remember { mutableStateOf(false) }
                            var isContentVisible by remember { mutableStateOf(false) }

                            LaunchedEffect(page) {
                                isTitleVisible = true
                                kotlinx.coroutines.delay(200)
                                isContentVisible = true
                            }

                            AnimatedVisibility(
                                visible = isTitleVisible,
                                enter = fadeIn(animationSpec = tween(500))
                            ) {
                                Text(
                                    text = article.title,
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = Color.White
                                )
                            }

                            Spacer(Modifier.height(16.dp))

                            AnimatedVisibility(
                                visible = isContentVisible,
                                enter = fadeIn(animationSpec = tween(500))
                            ) {
                                Text(
                                    text = article.content,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                // Индикаторы страниц
                AnimatedIndicatorRow(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    pagerState = pagerState,
                    pagesCount = articles.size
                )
            }
        }
    }
}

@Composable
private fun AnimatedIndicatorRow(
    modifier: Modifier,
    pagerState: PagerState,
    pagesCount: Int
) {
    val currentPage = pagerState.currentPage
    val pageOffset = pagerState.currentPageOffsetFraction

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(pagesCount) { i ->
            val targetWeight = when (i) {
                currentPage -> 3f - 2f * abs(pageOffset)
                currentPage + 1 -> 1f + 2f * max(0f, pageOffset)
                currentPage - 1 -> 1f + 2f * max(0f, -pageOffset)
                else -> 1f
            }.coerceAtLeast(1f)

            val animatedWeight by animateFloatAsState(targetValue = targetWeight)

            Box(
                modifier = Modifier
                    .weight(animatedWeight)
                    .height(5.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
            )
        }
    }
}