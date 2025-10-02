package com.coding.rewards_screen_impl.rewards_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ru.braveowlet.simple_mvi_example.core.resources.Res
import ru.braveowlet.simple_mvi_example.core.resources.box_celebrate

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RewardCard(
    reward: Reward,
    modifier: Modifier = Modifier,
    onRewardClick: (Reward) -> Unit = {}
) {
    val shape = RoundedCornerShape(16.dp)
    Card(
        onClick = { onRewardClick(reward) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .border(2.dp, Color(0xFFCFCFFF), shape),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = if (reward.isWon) Color(0xFF6088E4) else Color(0xFFBDCEFA)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.box_celebrate),
                contentDescription = reward.title,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = reward.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1919EF)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = reward.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}