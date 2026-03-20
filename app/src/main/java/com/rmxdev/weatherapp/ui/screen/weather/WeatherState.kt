package com.rmxdev.weatherapp.ui.screen.weather

import com.rmxdev.weatherapp.domain.model.WeatherInfo

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: WeatherInfo) : WeatherState()
    data class Error(val message: String) : WeatherState()
    object PermissionDenied : WeatherState()
    object GpsDisabled : WeatherState()
}