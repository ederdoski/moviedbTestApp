package com.edominguez.moviedb.features.home.maps.usecase

import com.edominguez.moviedb.features.home.maps.datasource.model.UserPositionResponseData
import com.edominguez.moviedb.features.home.maps.datasource.repository.FireStoreRepository
import com.google.android.gms.maps.model.LatLng
import org.joda.time.LocalDateTime


class FireStoreUseCase (private val fireStoreRepository: FireStoreRepository) {

    suspend fun getUserLocations(): List<UserPositionResponseData> {
        return fireStoreRepository.getUserLocations()
    }

    suspend fun saveOrUpdateNewLocation(deviceID: String, latLng: LatLng): Boolean {
        return fireStoreRepository.saveOrUpdateNewLocation(deviceID, latLng, LocalDateTime.now().toString())
    }

}