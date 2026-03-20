package com.rmxdev.weatherapp.ui.screen.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Weather : Screen("weather")
}