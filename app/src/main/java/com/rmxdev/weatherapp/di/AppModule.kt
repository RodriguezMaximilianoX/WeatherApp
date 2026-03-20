package com.rmxdev.weatherapp.di

import com.rmxdev.weatherapp.data.location.LocationDataSource
import com.rmxdev.weatherapp.data.remote.WeatherApiService
import com.rmxdev.weatherapp.data.repository.LocationRepositoryImpl
import com.rmxdev.weatherapp.data.repository.WeatherRepositoryImpl
import com.rmxdev.weatherapp.domain.repository.LocationRepository
import com.rmxdev.weatherapp.domain.repository.WeatherRepository
import com.rmxdev.weatherapp.domain.useCase.GetWeatherUseCase
import com.rmxdev.weatherapp.ui.screen.weather.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // HttpClient
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            // Podés agregar timeouts acá también
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 30_000
            }
        }
    }

    // DataSources
    single { WeatherApiService(get()) }
    single { LocationDataSource(get()) }

    // Repositories
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<LocationRepository> { LocationRepositoryImpl(get()) }

    // Use Cases
    factory { GetWeatherUseCase(get(), get()) }

    // ViewModel
    viewModel { WeatherViewModel(get()) }
}