package com.rmxdev.weatherapp.ui.screen.weather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rmxdev.weatherapp.R
import com.rmxdev.weatherapp.domain.model.WeatherInfo
import com.rmxdev.weatherapp.ui.theme.TextWhite

@Composable
fun MainWeatherSection(
    weather: WeatherInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Fila principal
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Izquierda — viento y lluvia
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_west),
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${weather.windSpeed} km/h",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextWhite
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_rain),
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${weather.rain}mm",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextWhite
                    )
                }
            }

            // Centro — ícono del clima + temperatura
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Ícono del clima desde OpenWeather
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${weather.iconCode}@2x.png",
                    contentDescription = weather.description,
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = "${weather.currentTemp}°",
                    color = TextWhite,
                    fontSize = 40.sp
                )
            }

            // Derecha — max y min
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_temp_max),
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${weather.maxTemp}°",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextWhite
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_temp_min),
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${weather.minTemp}°",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextWhite
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Sensación térmica
        Text(
            text = "Sensación de ${weather.feelsLike}°",
            style = MaterialTheme.typography.bodyMedium,
            color = TextWhite
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Descripción
        Text(
            text = weather.description,
            style = MaterialTheme.typography.bodyLarge,
            color = TextWhite
        )
    }
}