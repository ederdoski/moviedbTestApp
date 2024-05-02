package com.edominguez.moviedb.features.home.maps.usecase

import com.edominguez.moviedb.features.home.maps.datasource.model.UserPositionResponseData
import com.edominguez.moviedb.features.home.maps.datasource.repository.MapsRepository
import com.google.android.gms.maps.model.LatLng
import org.joda.time.LocalDateTime


class MapsUseCase(private val mapsRepository: MapsRepository) {

    suspend fun getUserLocations(): List<UserPositionResponseData> {
        return mapsRepository.getUserLocations()
    }

    suspend fun saveOrUpdateNewLocation(deviceID: String, latLng: LatLng): Boolean {
        return mapsRepository.saveOrUpdateNewLocation(deviceID, latLng, LocalDateTime.now().toString())
    }

}