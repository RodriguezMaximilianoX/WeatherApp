package com.rmxdev.weatherapp.ui.screen.weather

import com.rmxdev.weatherapp.MainDispatcherRule
import com.rmxdev.weatherapp.domain.model.WeatherInfo
import com.rmxdev.weatherapp.domain.useCase.GetWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getWeatherUseCase: GetWeatherUseCase = mockk()
    private lateinit var viewModel: WeatherViewModel

    private val fakeWeather = WeatherInfo(
        city = "Córdoba",
        dateTime = "26 de abril | 13:56",
        currentTemp = 20,
        feelsLike = 19,
        maxTemp = 22,
        minTemp = 18,
        description = "Parcialmente nublado",
        iconCode = "02d",
        pressure = 1018,
        clouds = 82,
        humidity = 34,
        windSpeed = 18,
        windGust = 37,
        windDegree = 260,
        rain = 2.5,
        sunrise = "6:41",
        sunset = "21:31"
    )

    @Before
    fun setup() {
        viewModel = WeatherViewModel(getWeatherUseCase)
    }

    @Test
    fun `estado inicial es Loading`() {
        assertTrue(viewModel.state.value is WeatherState.Loading)
    }

    @Test
    fun `cuando loadWeather es exitoso el state es Success`() = runTest {
        // ARRANGE
        coEvery { getWeatherUseCase() } returns Result.success(fakeWeather)

        // ACT
        viewModel.loadWeather()
        advanceUntilIdle()

        // ASSERT
        val state = viewModel.state.value
        assertTrue(state is WeatherState.Success)
        assertEquals(fakeWeather, (state as WeatherState.Success).weather)
    }

    @Test
    fun `cuando loadWeather falla con error de red el state es Error`() = runTest {
        // ARRANGE
        coEvery { getWeatherUseCase() } returns
                Result.failure(Exception("Sin conexión a internet"))

        // ACT
        viewModel.loadWeather()
        advanceUntilIdle()

        // ASSERT
        val state = viewModel.state.value
        assertTrue(state is WeatherState.Error)
        assertEquals(
            "Sin conexión a internet",
            (state as WeatherState.Error).message
        )
    }

    @Test
    fun `cuando loadWeather falla con error de GPS el state es GpsDisabled`() = runTest {
        // ARRANGE
        coEvery { getWeatherUseCase() } returns
                Result.failure(
                    Exception("GPS desactivado. Activá la ubicación e intentá de nuevo")
                )

        // ACT
        viewModel.loadWeather()
        advanceUntilIdle()

        // ASSERT
        assertTrue(viewModel.state.value is WeatherState.GpsDisabled)
    }

    @Test
    fun `cuando onPermissionDenied el state es PermissionDenied`() {
        // ACT
        viewModel.onPermissionDenied()

        // ASSERT
        assertTrue(viewModel.state.value is WeatherState.PermissionDenied)
    }

    @Test
    fun `cuando loadWeather inicia el state es Loading`() = runTest {
        // ARRANGE
        coEvery { getWeatherUseCase() } coAnswers {
            delay(1000)
            Result.success(fakeWeather)
        }

        // ACT
        viewModel.loadWeather()

        // ASSERT
        assertTrue(viewModel.state.value is WeatherState.Loading)
    }

    @Test
    fun `cuando loadWeather se llama dos veces el state es correcto`() = runTest {
        // ARRANGE
        coEvery { getWeatherUseCase() } returns Result.success(fakeWeather)

        // ACT
        viewModel.loadWeather()
        advanceUntilIdle()
        viewModel.loadWeather()
        advanceUntilIdle()

        // ASSERT — después de dos llamadas exitosas sigue siendo Success
        assertTrue(viewModel.state.value is WeatherState.Success)
    }

    @Test
    fun `despues de un error loadWeather puede recuperarse`() = runTest {
        // ARRANGE — primera llamada falla
        coEvery { getWeatherUseCase() } returns
                Result.failure(Exception("Sin conexión a internet"))

        viewModel.loadWeather()
        advanceUntilIdle()
        assertTrue(viewModel.state.value is WeatherState.Error)

        // Segunda llamada exitosa
        coEvery { getWeatherUseCase() } returns Result.success(fakeWeather)

        // ACT
        viewModel.loadWeather()
        advanceUntilIdle()

        // ASSERT — se recuperó del error
        assertTrue(viewModel.state.value is WeatherState.Success)
    }
}