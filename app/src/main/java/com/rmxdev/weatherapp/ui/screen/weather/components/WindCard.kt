package com.rmxdev.weatherapp.ui.screen.weather.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.weatherapp.R
import com.rmxdev.weatherapp.ui.theme.CardBorder
import com.rmxdev.weatherapp.ui.theme.TextWhite

@Composable
fun WindCard(
    windSpeed: Int,
    windGust: Int,
    windDegree: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        // Izquierda — datos del viento
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Título
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_air),
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Viento",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextWhite
                )
            }

            // Velocidad viento
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "$windSpeed",
                    style = MaterialTheme.typography.displayLarge.copy(fontSize = 36.sp),
                    color = TextWhite
                )
                Column {
                    Text(
                        text = "km/h",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextWhite.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "Viento",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextWhite.copy(alpha = 0.7f)
                    )
                }
            }


            HorizontalDivider(
                modifier = Modifier.width(190.dp),
                thickness = 1.dp,
                color = CardBorder
            )

            // Rachas
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "$windGust",
                    style = MaterialTheme.typography.displayLarge.copy(fontSize = 36.sp),
                    color = TextWhite
                )
                Column {
                    Text(
                        text = "km/h",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextWhite.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "Rachas",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextWhite.copy(alpha = 0.7f)
                    )
                }
            }
        }

        // Brújula
        WindCompass(degree = windDegree)
    }
}