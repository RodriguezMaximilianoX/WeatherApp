package com.rmxdev.weatherapp.ui.screen.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rmxdev.weatherapp.R
import com.rmxdev.weatherapp.ui.theme.CardBorder
import com.rmxdev.weatherapp.ui.theme.TextWhite

@Composable
fun SunsetCard(
    sunrise: String,
    sunset: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.run {
            fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp)
        },
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Título
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_twilight),
                contentDescription = null,
                tint = TextWhite,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Puesta de sol",
                style = MaterialTheme.typography.titleLarge,
                color = TextWhite
            )
        }

        // Fila de datos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Anochecer — izquierda
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Anochece $sunset",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextWhite
                )
                Image(
                    painter = painterResource(R.drawable.ic_sunset),
                    contentDescription = "Puesta de sol",
                    modifier = Modifier.size(52.dp)
                )
            }

            // Amanece — derecha
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Amanece $sunrise",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextWhite
                )
                Image(
                    painter = painterResource(R.drawable.ic_sunrise),
                    contentDescription = "Amanecer",
                    modifier = Modifier.size(52.dp)
                )
            }
        }
    }
}