package com.rmxdev.weatherapp.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.rmxdev.weatherapp.domain.model.UserLocation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.resumeWithException

class LocationDataSource(context: Context) {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    fun isGpsEnabled(): Boolean =
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    fun isNetworkEnabled(): Boolean =
        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    @SuppressLint("MissingPermission")
    suspend fun getLocation(): Result<UserLocation> {
        val lastLocation = getLastKnownLocation()
        if (lastLocation != null) {
            return Result.success(lastLocation)
        }
        return runCatching { requestLocationUpdate() }
            .fold(
                onSuccess = { Result.success(it) },
                onFailure = {
                    if (it is kotlinx.coroutines.TimeoutCancellationException) {
                        Result.failure(Exception("No pudimos obtener tu ubicación. Intentá de nuevo"))
                    } else {
                        Result.failure(it)
                    }
                }
            )
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(): UserLocation? {
        val location =
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        return location?.let { UserLocation(it.latitude, it.longitude) }
    }

    @SuppressLint("MissingPermission")
    private suspend fun requestLocationUpdate(): UserLocation {
        val provider = when {
            isGpsEnabled() -> LocationManager.GPS_PROVIDER
            isNetworkEnabled() -> LocationManager.NETWORK_PROVIDER
            else -> throw Exception(
                "GPS no disponible. Activá la ubicación en tu dispositivo"
            )
        }

        return withTimeout(10_000L) {
            suspendCancellableCoroutine { continuation ->
                val listener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        locationManager.removeUpdates(this)
                        continuation.resume(
                            UserLocation(
                                location.latitude,
                                location.longitude
                            )
                        ) { cause, _, _ ->
                            locationManager.removeUpdates(this)
                        }
                    }

                    override fun onProviderDisabled(provider: String) {
                        locationManager.removeUpdates(this)
                        continuation.resumeWithException(
                            Exception("GPS desactivado. Activá la ubicación e intentá de nuevo")
                        )
                    }
                }

                locationManager.requestLocationUpdates(
                    provider, 0L, 0f, listener
                )

                continuation.invokeOnCancellation {
                    locationManager.removeUpdates(listener)
                }
            }
        }
    }
}