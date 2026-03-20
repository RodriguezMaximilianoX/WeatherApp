package com.rmxdev.weatherapp.ui.screen.weather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rmxdev.weatherapp.domain.model.WeatherInfo
import com.rmxdev.weatherapp.ui.theme.ArcClouds
import com.rmxdev.weatherapp.ui.theme.ArcHumidity
import com.rmxdev.weatherapp.ui.theme.ArcPressure

@Composable
fun StatsRow(
    weather: WeatherInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Presión
        CircularStat(
            value = "${weather.pressure}",
            unit = "hPa",
            label = "Presión",
            progress = (weather.pressure / 1050f).coerceIn(0f, 1f),
            arcColor = ArcPressure
        )

        // Nubes
        CircularStat(
            value = "${weather.clouds}%",
            unit = "",
            label = "Nubes",
            progress = weather.clouds / 100f,
            arcColor = ArcClouds
        )

        // Humedad
        CircularStat(
            value = "${weather.humidity}%",
            unit = "",
            label = "Humedad",
            progress = weather.humidity / 100f,
            arcColor = ArcHumidity
        )
    }
}