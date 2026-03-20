package com.rmxdev.weatherapp.domain.repository

import com.rmxdev.weatherapp.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Result<WeatherInfo>
}