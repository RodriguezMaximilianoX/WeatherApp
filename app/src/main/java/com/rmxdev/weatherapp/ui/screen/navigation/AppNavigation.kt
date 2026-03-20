package com.rmxdev.weatherapp.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmxdev.weatherapp.ui.screen.splash.SplashScreen
import com.rmxdev.weatherapp.ui.screen.weather.WeatherScreenWithPermissions

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Splash
        composable(Screen.Splash.route) {
            SplashScreen(
                onComenzarClick = {
                    navController.navigate(Screen.Weather.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Weather — maneja permisos internamente
        composable(Screen.Weather.route) {
            WeatherScreenWithPermissions()
        }
    }
}