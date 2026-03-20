package com.rmxdev.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(

    // Temperatura principal — "20°"
    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp
    ),

    // Título de sección — "Viento", "Puesta de sol"
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    // Velocidad del viento — "18 km/h"
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),

    // Descripción — "Parcialmente nublado"
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    // Texto secundario — "Viento", "Rachas"
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    // Labels pequeños — "hPa", "km/h"
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),

    // Fecha — "26 de abril | 13:56"
    labelLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)