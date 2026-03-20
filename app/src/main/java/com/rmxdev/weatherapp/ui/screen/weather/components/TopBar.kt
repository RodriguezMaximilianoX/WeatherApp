package com.rmxdev.weatherapp.ui.screen.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.sp
import com.rmxdev.weatherapp.R
import com.rmxdev.weatherapp.ui.theme.TextWhite

@Composable
fun TopBar(
    city: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Settings
        Icon(
            painter = painterResource(id = R.drawable.ic_settings),
            contentDescription = "Configuración",
            tint = TextWhite,
            modifier = Modifier.size(24.dp)
        )

        // Pill — ubicación + ciudad
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .width(232.dp)
                .height(40.dp)
                .background(TextWhite.copy(alpha = 0.3f))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                tint = TextWhite,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = city,
                color = TextWhite,
                fontSize = 16.sp
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Buscar",
                tint = TextWhite,
                modifier = Modifier.size(24.dp)
            )
        }

        // Share
        Icon(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "Compartir",
            tint = TextWhite,
            modifier = Modifier.size(24.dp)
        )
    }
}