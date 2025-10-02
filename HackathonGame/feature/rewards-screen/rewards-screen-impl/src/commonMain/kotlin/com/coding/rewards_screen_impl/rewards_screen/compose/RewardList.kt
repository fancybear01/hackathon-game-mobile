package com.coding.rewards_screen_impl.rewards_screen.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RewardList(rewards: List<Reward>, onRewardClick:(Reward) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(rewards) { reward ->
            RewardCard(reward = reward, onRewardClick = onRewardClick)
        }
    }
}