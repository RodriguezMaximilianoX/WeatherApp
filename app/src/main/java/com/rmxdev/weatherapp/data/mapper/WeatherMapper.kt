package com.rmxdev.weatherapp.data.mapper

import com.rmxdev.weatherapp.data.remote.dto.WeatherResponse
import com.rmxdev.weatherapp.domain.model.WeatherInfo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

fun WeatherResponse.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        city = name,
        dateTime = dt.toFormattedDate(),
        currentTemp = main.temp.kelvinToCelsius(),
        feelsLike = main.feels_like.kelvinToCelsius(),
        maxTemp = main.temp_max.kelvinToCelsius(),
        minTemp = main.temp_min.kelvinToCelsius(),
        description = weather.firstOrNull()?.description
            ?.replaceFirstChar { it.uppercase() } ?: "",
        iconCode = weather.firstOrNull()?.icon ?: "",
        pressure = main.pressure,
        clouds = clouds.all,
        humidity = main.humidity,
        windSpeed = wind.speed.metersPerSecondToKmh(),
        windGust = wind.gust?.metersPerSecondToKmh() ?: 0,
        windDegree = wind.deg,
        rain = rain?.oneHour ?: 0.0,
        sunrise = sys.sunrise.toFormattedTime(),
        sunset = sys.sunset.toFormattedTime()
    )
}

private fun Double.kelvinToCelsius(): Int =
    (this - 273.15).roundToInt()

private fun Double.metersPerSecondToKmh(): Int =
    (this * 3.6).roundToInt()

private fun Long.toFormattedTime(): String {
    val sdf = SimpleDateFormat("H:mm", Locale.getDefault())
    return sdf.format(Date(this * 1000))
}

private fun Long.toFormattedDate(): String {
    val sdf = SimpleDateFormat("d 'de' MMMM | HH:mm", Locale("es"))
    return sdf.format(Date(this * 1000))
}