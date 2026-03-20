package com.rmxdev.weatherapp.domain.useCase

import com.rmxdev.weatherapp.domain.model.WeatherInfo
import com.rmxdev.weatherapp.domain.repository.LocationRepository
import com.rmxdev.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Result<WeatherInfo> {
        val locationResult = locationRepository.getLocation()

        val location = locationResult.getOrElse { error ->
            return Result.failure(error)
        }

        return weatherRepository.getWeather(
            latitude = location.latitude,
            longitude = location.longitude
        )
    }
}