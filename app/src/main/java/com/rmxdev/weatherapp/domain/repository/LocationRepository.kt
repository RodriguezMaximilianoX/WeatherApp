package com.rmxdev.weatherapp.domain.repository

import com.rmxdev.weatherapp.domain.model.UserLocation

interface LocationRepository {
    suspend fun getLocation(): Result<UserLocation>
}