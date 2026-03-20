package com.rmxdev.weatherapp.domain.useCase

import com.rmxdev.weatherapp.domain.model.UserLocation
import com.rmxdev.weatherapp.domain.model.WeatherInfo
import com.rmxdev.weatherapp.domain.repository.LocationRepository
import com.rmxdev.weatherapp.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherUseCaseTest {

    private val weatherRepository: WeatherRepository = mockk()
    private val locationRepository: LocationRepository = mockk()
    private lateinit var useCase: GetWeatherUseCase

    private val fakeLocation = UserLocation(
        latitude = -31.4201,
        longitude = -64.1888
    )

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
        useCase = GetWeatherUseCase(weatherRepository, locationRepository)
    }

    @Test
    fun `cuando ubicacion y clima son exitosos retorna Success`() = runTest {
        // ARRANGE
        coEvery { locationRepository.getLocation() } returns
                Result.success(fakeLocation)
        coEvery {
            weatherRepository.getWeather(
                fakeLocation.latitude,
                fakeLocation.longitude
            )
        } returns Result.success(fakeWeather)

        // ACT
        val result = useCase()

        // ASSERT
        assertTrue(result.isSuccess)
        assertEquals(fakeWeather, result.getOrNull())
    }

    @Test
    fun `cuando getLocation falla no se llama a getWeather`() = runTest {
        // ARRANGE
        coEvery { locationRepository.getLocation() } returns
                Result.failure(Exception("GPS desactivado. Activá la ubicación e intentá de nuevo"))

        // ACT
        val result = useCase()

        // ASSERT
        assertTrue(result.isFailure)
        assertEquals(
            "GPS desactivado. Activá la ubicación e intentá de nuevo",
            result.exceptionOrNull()?.message
        )
        coVerify(exactly = 0) { weatherRepository.getWeather(any(), any()) }
    }

    @Test
    fun `cuando getWeather falla retorna el error de red`() = runTest {
        // ARRANGE
        coEvery { locationRepository.getLocation() } returns
                Result.success(fakeLocation)
        coEvery {
            weatherRepository.getWeather(any(), any())
        } returns Result.failure(Exception("Sin conexión a internet"))

        // ACT
        val result = useCase()

        // ASSERT
        assertTrue(result.isFailure)
        assertEquals(
            "Sin conexión a internet",
            result.exceptionOrNull()?.message
        )
    }

    @Test
    fun `getWeather se llama con las coordenadas de getLocation`() = runTest {
        // ARRANGE
        coEvery { locationRepository.getLocation() } returns
                Result.success(fakeLocation)
        coEvery {
            weatherRepository.getWeather(
                fakeLocation.latitude,
                fakeLocation.longitude
            )
        } returns Result.success(fakeWeather)

        // ACT
        useCase()

        // ASSERT
        coVerify(exactly = 1) {
            weatherRepository.getWeather(
                latitude = fakeLocation.latitude,
                longitude = fakeLocation.longitude
            )
        }
    }

    @Test
    fun `el resultado contiene exactamente los datos del repositorio`() = runTest {
        // ARRANGE
        coEvery { locationRepository.getLocation() } returns
                Result.success(fakeLocation)
        coEvery {
            weatherRepository.getWeather(any(), any())
        } returns Result.success(fakeWeather)

        // ACT
        val result = useCase()
        val weather = result.getOrNull()

        // ASSERT
        assertEquals("Córdoba", weather?.city)
        assertEquals(20, weather?.currentTemp)
        assertEquals(34, weather?.humidity)
        assertEquals("6:41", weather?.sunrise)
    }
}