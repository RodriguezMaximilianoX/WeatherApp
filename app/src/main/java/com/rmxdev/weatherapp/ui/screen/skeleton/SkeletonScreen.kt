package com.rmxdev.weatherapp.ui.screen.skeleton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rmxdev.weatherapp.ui.screen.skeleton.components.SkeletonBlock
import com.rmxdev.weatherapp.ui.theme.BackgroundBlue

@Composable
fun SkeletonScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundBlue)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // TopBar skeleton
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkeletonBlock(
                modifier = Modifier
                    .height(32.dp)
                    .width(76.dp),
                cornerRadius = 4.dp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ubicación skeleton
        SkeletonBlock(
            modifier = Modifier
                .width(328.dp)
                .height(40.dp)
                .align(Alignment.CenterHorizontally),
            cornerRadius = 4.dp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sección principal skeleton
        SkeletonBlock(
            modifier = Modifier
                .width(328.dp)
                .height(156.dp),
            cornerRadius = 4.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stats row skeleton
        SkeletonBlock(
            modifier = Modifier
                .width(328.dp)
                .height(74.dp),
            cornerRadius = 4.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Wind card skeleton
        SkeletonBlock(
            modifier = Modifier
                .width(328.dp)
                .height(144.dp),
            cornerRadius = 4.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sunset card skeleton
        SkeletonBlock(
            modifier = Modifier
                .width(328.dp)
                .height(118.dp),
            cornerRadius = 4.dp
        )
    }
}