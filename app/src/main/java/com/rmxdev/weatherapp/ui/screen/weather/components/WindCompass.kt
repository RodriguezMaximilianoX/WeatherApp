package com.rmxdev.weatherapp.ui.screen.weather.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rmxdev.weatherapp.ui.theme.CardBackground
import com.rmxdev.weatherapp.ui.theme.CardBorder
import com.rmxdev.weatherapp.ui.theme.CompassNeedle
import com.rmxdev.weatherapp.ui.theme.TextPrimary

@Composable
fun WindCompass(
    degree: Int,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(100.dp)
    ) {
        // Círculo de fondo
        Canvas(modifier = Modifier.size(100.dp)) {
            drawCircle(
                color = CardBackground,
                radius = size.minDimension / 2
            )
            drawCircle(
                color = CardBorder,
                radius = size.minDimension / 2,
                style = Stroke(width = 1.dp.toPx())
            )
        }

        // Puntos cardinales
        Box(
            modifier = Modifier.size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "N",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 4.dp)
            )
            Text(
                text = "S",
                style = MaterialTheme.typography.bodySmall,
                color = TextPrimary,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp)
            )
            Text(
                text = "E",
                style = MaterialTheme.typography.bodySmall,
                color = TextPrimary,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 4.dp)
            )
            Text(
                text = "W",
                style = MaterialTheme.typography.bodySmall,
                color = TextPrimary,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 4.dp)
            )
        }

        // Grados en el centro
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(CardBorder.copy(alpha = 0.5f))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${degree}°",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = degreeToDirection(degree),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextPrimary.copy(alpha = 0.7f)
                )
            }
        }

        // Punto rojo en el borde indicando la dirección
        Canvas(
            modifier = Modifier
                .size(100.dp)
                .rotate(degree.toFloat())
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2

            // Radio del círculo principal menos un margen
            // para que el punto quede justo sobre el borde
            val orbitRadius = size.minDimension / 2 - 8.dp.toPx()

            // Posición del punto — siempre apunta hacia arriba (Norte)
            // la rotación del Canvas se encarga de orientarlo correctamente
            val dotX = centerX
            val dotY = centerY - orbitRadius

            // Sombra del punto para mejor visibilidads
            drawCircle(
                color = CompassNeedle.copy(alpha = 0.3f),
                radius = 7.dp.toPx(),
                center = Offset(dotX, dotY)
            )

            // Punto rojo principal
            drawCircle(
                color = CompassNeedle,
                radius = 5.dp.toPx(),
                center = Offset(dotX, dotY)
            )
        }
    }
}

// Convierte grados a dirección cardinal
fun degreeToDirection(degree: Int): String {
    return when (degree) {
        in 0..22 -> "N"
        in 23..67 -> "NE"
        in 68..112 -> "E"
        in 113..157 -> "SE"
        in 158..202 -> "S"
        in 203..247 -> "SO"
        in 248..292 -> "O"
        in 293..337 -> "NO"
        else -> "N"
    }
}