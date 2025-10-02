package com.coding.rewards_screen_impl.rewards_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.coding.mvi_koin_voyager.MviView
import com.coding.mvi_koin_voyager.collectEvent
import com.coding.rewards_screen_impl.rewards_screen.compose.Reward
import com.coding.rewards_screen_impl.rewards_screen.compose.RewardsScreenContent
import com.coding.rewards_screen_impl.rewards_screen.mvi.RewardsAction
import com.coding.rewards_screen_impl.rewards_screen.mvi.RewardsEvent
import com.coding.rewards_screen_impl.rewards_screen.mvi.RewardsState
import kotlinx.coroutines.flow.Flow

internal class RewardsScreen : MviView<RewardsAction, RewardsEvent, RewardsState> {

    @Composable
    override fun content(
        state: RewardsState,
        eventFlow: Flow<RewardsEvent>,
        pushAction: (RewardsAction) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow

        // Обработка событий
        eventFlow.collectEvent { event ->
            when (event) {
                RewardsEvent.NavigateToMainScreen -> navigator.pop()
            }
        }

        val wonRewards = listOf(
            Reward(id = "w1", title = "Стикер‑пак приложения", description = "Набор стикеров для Telegram/VK", imageUrl = "", isWon = true),
            Reward(id = "w2", title = "Промокод −10% на курс", description = "Скидка на любой платный курс, действует 30 дней", imageUrl = "", isWon = true),
            Reward(id = "w3", title = "Бейдж ‘Первопроходец’", description = "Редкий бейдж профиля за первые достижения", imageUrl = "", isWon = true),
            Reward(id = "w4", title = "500 бонусных монет", description = "Можно обменять на призы в магазине", imageUrl = "", isWon = true),
            Reward(id = "w5", title = "Билет на розыгрыш", description = "Автоматическое участие в ближайшем еженедельном розыгрыше", imageUrl = "", isWon = true)
        )

        val availableRewards = listOf(
            Reward(id = "a1", title = "Футболка с логотипом", description = "Размер можно выбрать при оформлении", imageUrl = "", isWon = false),
            Reward(id = "a2", title = "Кружка‑разработчика", description = "Керамическая кружка 350 мл", imageUrl = "", isWon = false),
            Reward(id = "a3", title = "Подписка PRO на 1 месяц", description = "Доступ к дополнительным материалам и квизам", imageUrl = "", isWon = false),
            Reward(id = "a4", title = "Промокод −15% на мерч", description = "Действует в официальном магазине", imageUrl = "", isWon = false),
            Reward(id = "a5", title = "Доступ к мини‑курсу ‘Kotlin Basics’", description = "Материалы и задания на 7 дней", imageUrl = "", isWon = false),
            Reward(id = "a6", title = "Сертификат Ozon на 500 ₽", description = "Электронный промокод, приходит на почту", imageUrl = "", isWon = false)
        )

        // Подключаем UI
        RewardsScreenContent(
            wonRewards = wonRewards,
            availableRewards = availableRewards,
            onRewardClick = { reward ->
                pushAction(RewardsAction.OnRewardClick(reward.id))
            },
            onBackClick = { navigator.pop() }
        )
    }
}