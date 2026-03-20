package com.rmxdev.weatherapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val name: String,
    val dt: Long,
    val main: MainDto,
    val weather: List<WeatherDto>,
    val wind: WindDto,
    val clouds: CloudsDto,
    val rain: RainDto? = null,
    val sys: SysDto
)

@Serializable
data class MainDto(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

@Serializable
data class WeatherDto(
    val description: String,
    val icon: String
)

@Serializable
data class WindDto(
    val speed: Double,
    val deg: Int,
    val gust: Double? = null
)

@Serializable
data class CloudsDto(
    val all: Int
)

@Serializable
data class RainDto(
    @SerialName("1h")
    val oneHour: Double? = null
)

@Serializable
data class SysDto(
    val sunrise: Long,
    val sunset: Long
)