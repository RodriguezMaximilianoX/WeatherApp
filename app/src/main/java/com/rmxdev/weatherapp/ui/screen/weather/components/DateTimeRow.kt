package com.rmxdev.weatherapp.ui.screen.weather.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.rmxdev.weatherapp.ui.theme.TextWhite

@Composable
fun DateTimeRow(
    dateTime: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = dateTime,
        style = MaterialTheme.typography.labelLarge,
        color = TextWhite,
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}