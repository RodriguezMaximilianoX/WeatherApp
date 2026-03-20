package com.rmxdev.weatherapp.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.weatherapp.R
import com.rmxdev.weatherapp.ui.theme.*

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onComenzarClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_carreras_logo),
            contentDescription = "Carreras Logo",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 74.dp, horizontal = 80.dp)
                .width(200.dp)
        )

        Button(
            onClick = onComenzarClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = TextWhite,
                contentColor = TextPrimary
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 94.dp)
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(
                text = "Comenzar",
                color = TextPrimary,
                fontSize = 32.sp,
            )
        }
    }
}