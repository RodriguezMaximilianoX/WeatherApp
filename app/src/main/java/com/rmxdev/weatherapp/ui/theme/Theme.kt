package com.rmxdev.weatherapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val WeatherColorScheme = lightColorScheme(
    primary = BackgroundBlue,
    onPrimary = TextWhite,
    background = BackgroundBlue,
    onBackground = TextWhite,
    surface = BackgroundBlue,
    onSurface = TextWhite,
    onSurfaceVariant = TextWhite.copy(alpha = 0.7f),
    outline = CardBorder
)

@Composable
fun WeatherAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WeatherColorScheme,
        typography = Typography,
        content = content
    )
}