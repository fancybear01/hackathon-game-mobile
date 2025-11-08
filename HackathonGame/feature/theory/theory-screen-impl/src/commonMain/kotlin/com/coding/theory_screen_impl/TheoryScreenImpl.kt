package com.coding.theory_screen_impl

import cafe.adriel.voyager.core.screen.Screen
import com.coding.theory_screen_api.TheoryScreenApi
import com.coding.theory_screen_impl.theory_screen.TheoryScreen

internal class TheoryScreenImpl: TheoryScreenApi {
    override fun theoryScreen(id: Int): Screen = TheoryScreen(id)
}