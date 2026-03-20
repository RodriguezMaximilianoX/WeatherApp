package com.rmxdev.weatherapp.data.remote

import com.rmxdev.weatherapp.BuildConfig
import com.rmxdev.weatherapp.data.remote.dto.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json

class WeatherApiService(
    private val client: HttpClient
) {
    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Result<WeatherResponse> {
        return try {
            val response = client.get(BuildConfig.BASE_URL) {
                parameter("lat", latitude)
                parameter("lon", longitude)
                parameter("appid", BuildConfig.API_KEY)
                parameter("lang", "es")
            }.body<WeatherResponse>()
            Result.success(response)
        } catch (e: IOException) {
            Result.failure(Exception("Sin conexión a internet"))
        } catch (e: Exception) {
            Result.failure(Exception("Error al obtener el clima. Intentá de nuevo"))
        }
    }
}