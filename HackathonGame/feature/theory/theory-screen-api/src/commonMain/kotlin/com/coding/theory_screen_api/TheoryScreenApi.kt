package com.coding.theory_screen_api

import cafe.adriel.voyager.core.screen.Screen

interface TheoryScreenApi {
    fun theoryScreen(id: Int) : Screen
}