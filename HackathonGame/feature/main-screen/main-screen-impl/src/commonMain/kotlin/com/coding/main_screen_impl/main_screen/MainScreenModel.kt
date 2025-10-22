package com.coding.main_screen_impl.main_screen

import com.coding.components.pet.domain.usecase.GetPetUseCase
import com.coding.main_screen_impl.main_screen.mvi.MainScreenAction
import com.coding.main_screen_impl.main_screen.mvi.MainScreenEffect
import com.coding.main_screen_impl.main_screen.mvi.MainScreenEvent
import com.coding.main_screen_impl.main_screen.mvi.MainScreenState
import com.coding.mvi_koin_voyager.MviModel

val userid = 1

internal class MainScreenModel(
    tag: String,
    private val getPet: GetPetUseCase
) : MviModel<MainScreenAction, MainScreenEffect, MainScreenEvent, MainScreenState>(
    defaultState = MainScreenState.DEFAULT,
    tag = tag,
) {
    override suspend fun bootstrap() {
        getPet(
            id = userid
        )
            .onSuccess { pet -> push(MainScreenEffect.SetPet(pet)) }
            .onFailure { /* TODO: обработать ошибку */ }
    }

    override suspend fun actor(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.ClickButtonOnCourse -> {
                push(MainScreenEvent.NavigateToCourseScreen)
            }

            is MainScreenAction.ClickButtonOnDailyTasks -> {
                push(MainScreenEffect.ShowDialogDailyTasks)
            }

            is MainScreenAction.ClickMenuRewards -> {
                push(MainScreenEvent.NavigateToRewardsScreen)
            }
        }
    }

    override fun reducer(
        effect: MainScreenEffect,
        previousState: MainScreenState
    ): MainScreenState =
        when (effect) {
            is MainScreenEffect.ShowDialogDailyTasks -> previousState
            is MainScreenEffect.SetPet -> previousState.copy(
                pet = effect.pet
            )
        }

}