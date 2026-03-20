package com.rmxdev.weatherapp.ui.screen.weather

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreenWithPermissions(
    viewModel: WeatherViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val pedirPermiso = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { otorgado ->
        if (otorgado) {
            viewModel.loadWeather()
        } else {
            viewModel.onPermissionDenied()
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                when {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        viewModel.loadWeather()
                    }
                    else -> {
                        if (viewModel.state.value !is WeatherState.Loading) {
                            viewModel.onPermissionDenied()
                        }
                    }
                }
            }

            if (event == Lifecycle.Event.ON_START) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    pedirPermiso.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    WeatherScreen(viewModel = viewModel)
}