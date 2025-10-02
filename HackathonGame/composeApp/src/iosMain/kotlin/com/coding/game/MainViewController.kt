package com.coding.game

import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.material3.MaterialTheme
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.koinInject
import org.koin.core.context.startKoin
import com.coding.logger.Logger
import com.coding.common.utils.ThreadUtils
import com.coding.onboarding.onboarding_api.OnboardingApi

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController {
    val onboardingApi = koinInject<OnboardingApi>()
    MaterialTheme {
        Navigator(
            onboardingApi.onboardingScreen()
        )
    }
}

@Suppress("unused")
fun initApp(
    iosUtils: IosUtils
) {
    initKoin()
    Logger.init()
    ThreadUtils.init(iosUtils::getThreadName)
}

private fun initKoin() {
    startKoin {
        modules(
            appModules
        )
    }
}