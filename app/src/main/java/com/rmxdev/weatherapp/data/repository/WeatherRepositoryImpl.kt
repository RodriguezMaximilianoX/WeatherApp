package com.rmxdev.weatherapp.data.repository

import com.rmxdev.weatherapp.data.mapper.toWeatherInfo
import com.rmxdev.weatherapp.data.remote.WeatherApiService
import com.rmxdev.weatherapp.domain.model.WeatherInfo
import com.rmxdev.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Result<WeatherInfo> {
        return apiService.getWeather(latitude, longitude)
            .map { response -> response.toWeatherInfo() }
    }
}