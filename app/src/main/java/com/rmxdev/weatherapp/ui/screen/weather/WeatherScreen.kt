package com.rmxdev.weatherapp.ui.screen.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rmxdev.weatherapp.R
import com.rmxdev.weatherapp.domain.model.WeatherInfo
import com.rmxdev.weatherapp.ui.screen.skeleton.SkeletonScreen
import com.rmxdev.weatherapp.ui.screen.weather.components.DateTimeRow
import com.rmxdev.weatherapp.ui.screen.weather.components.MainWeatherSection
import com.rmxdev.weatherapp.ui.screen.weather.components.StatsRow
import com.rmxdev.weatherapp.ui.screen.weather.components.SunsetCard
import com.rmxdev.weatherapp.ui.screen.weather.components.TopBar
import com.rmxdev.weatherapp.ui.screen.weather.components.WindCard
import com.rmxdev.weatherapp.ui.theme.BackgroundBlue
import com.rmxdev.weatherapp.ui.theme.TextWhite
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is WeatherState.Loading -> SkeletonScreen()
        is WeatherState.GpsDisabled -> ErrorScreen(
            mensaje = "GPS desactivado. Activá la ubicación e intentá de nuevo.",
            onReintentar = { viewModel.loadWeather() }
        )
        is WeatherState.Error -> ErrorScreen(
            mensaje = (state as WeatherState.Error).message,
            onReintentar = { viewModel.loadWeather() }
        )
        is WeatherState.PermissionDenied -> PermissionDeniedScreen()
        is WeatherState.Success -> WeatherContent(
            weather = (state as WeatherState.Success).weather
        )
    }
}

@Composable
fun WeatherContent(
    weather: WeatherInfo
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlue)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 128.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_location),
                contentDescription = null,
                tint = TextWhite,
                modifier = Modifier.size(16.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_circle),
                contentDescription = null,
                tint = TextWhite,
                modifier = Modifier.size(12.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_circle),
                contentDescription = null,
                tint = TextWhite,
                modifier = Modifier.size(12.dp)
            )
        }

        // TopBar
        TopBar(city = weather.city)

        Spacer(modifier = Modifier.height(24.dp))

        // Fecha y hora
        DateTimeRow(dateTime = weather.dateTime)

        Spacer(modifier = Modifier.height(24.dp))

        // Temperatura y datos principales
        MainWeatherSection(weather = weather)

        Spacer(modifier = Modifier.height(24.dp))

        // Presión, Nubes, Humedad
        StatsRow(weather = weather)

        Spacer(modifier = Modifier.height(16.dp))

        // Viento
        WindCard(
            windSpeed = weather.windSpeed,
            windGust = weather.windGust,
            windDegree = weather.windDegree
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Puesta de sol
        SunsetCard(
            sunrise = weather.sunrise,
            sunset = weather.sunset
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}