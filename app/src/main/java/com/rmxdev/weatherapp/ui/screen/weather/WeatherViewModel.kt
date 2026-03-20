package com.rmxdev.weatherapp.ui.screen.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.weatherapp.domain.useCase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeather: GetWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state = _state.asStateFlow()

    fun loadWeather() {
        viewModelScope.launch {
            _state.value = WeatherState.Loading
            getWeather()
                .onSuccess { weather ->
                    _state.value = WeatherState.Success(weather)
                }
                .onFailure { error ->
                    _state.value = when {
                        error.message?.contains("GPS") == true ->
                            WeatherState.GpsDisabled
                        else ->
                            WeatherState.Error(
                                error.message ?: "Error al obtener el clima"
                            )
                    }
                }
        }
    }

    fun onPermissionDenied() {
        _state.value = WeatherState.PermissionDenied
    }
}