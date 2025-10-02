package com.coding.rewards_screen_impl.rewards_screen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import ru.braveowlet.simple_mvi_example.core.resources.Res
import ru.braveowlet.simple_mvi_example.core.resources.arrow_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreenContent(
    wonRewards: List<Reward>,
    availableRewards: List<Reward>,
    onRewardClick: (Reward) -> Unit,
    onBackClick: () -> Unit
) {
    var selectedList by remember { mutableStateOf("Полученные") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Награды", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF1919EF), Color(0xFF8694E4))
                    )
                )
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Переключатель списков в стиле чипов
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Полученные", "Можно выиграть").forEach { item ->
                    val selected = selectedList == item
                    TextButton(
                        onClick = { selectedList = item },
                        modifier = Modifier
                            .weight(1f)
                            .border(2.dp, Color.White, RoundedCornerShape(50))
                            .background(if (selected) Color.White.copy(alpha = 0.15f) else Color.Transparent, RoundedCornerShape(50))
                    ) {
                        Text(
                            text = item,
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            val rewardsToShow = when (selectedList) {
                "Полученные" -> wonRewards
                "Можно выиграть" -> availableRewards
                else -> wonRewards
            }

            RewardList(rewards = rewardsToShow, onRewardClick = onRewardClick)
        }
    }
}