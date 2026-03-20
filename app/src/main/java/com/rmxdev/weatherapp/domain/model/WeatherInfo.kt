package com.rmxdev.weatherapp.domain.model

data class WeatherInfo(
    val city: String,
    val dateTime: String,
    val currentTemp: Int,
    val feelsLike: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val description: String,
    val iconCode: String,
    val pressure: Int,
    val clouds: Int,
    val humidity: Int,
    val windSpeed: Int,
    val windGust: Int,
    val windDegree: Int,
    val rain: Double,
    val sunrise: String,
    val sunset: String
)