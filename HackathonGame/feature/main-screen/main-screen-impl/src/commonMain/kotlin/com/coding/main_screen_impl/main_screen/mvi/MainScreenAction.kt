package com.coding.main_screen_impl.main_screen.mvi

import com.coding.mvi_general.MviAction

internal sealed interface MainScreenAction : MviAction {
    data object ClickButtonOnCourse : MainScreenAction
    data object ClickButtonOnDailyTasks : MainScreenAction
    data object ClickMenuRewards : MainScreenAction
}