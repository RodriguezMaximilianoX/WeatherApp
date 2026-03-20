package com.rmxdev.weatherapp.data.repository

import com.rmxdev.weatherapp.data.location.LocationDataSource
import com.rmxdev.weatherapp.domain.model.UserLocation
import com.rmxdev.weatherapp.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val dataSource: LocationDataSource
) : LocationRepository {

    override suspend fun getLocation(): Result<UserLocation> {
        return dataSource.getLocation()
    }
}