package com.rmxdev.weatherapp.ui.screen.weather

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreenWithPermissions(
    viewModel: WeatherViewModel = koinViewModel()
) {
    val context = LocalContext.current

    // Launcher para pedir el permiso
    val pedirPermiso = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { otorgado ->
        if (otorgado) {
            viewModel.loadWeather()
        } else {
            viewModel.onPermissionDenied()
        }
    }

    // Al entrar a la pantalla verificamos el permiso
    LaunchedEffect(Unit) {
        when {
            // CASO 1 — Ya tiene el permiso
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.loadWeather()
            }

            // CASO 2 y 3 — Pedir el permiso
            else -> {
                pedirPermiso.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    // UI según el estado
    WeatherScreen(viewModel = viewModel)
}