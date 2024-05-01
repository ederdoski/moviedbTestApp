package com.edominguez.moviedb.features.home.maps.usecase

import com.edominguez.moviedb.features.home.maps.datasource.model.UserPositionResponseData
import com.edominguez.moviedb.features.home.maps.datasource.repository.FireStoreRepository


class FireStoreUseCase (private val fireStoreRepository: FireStoreRepository) {

    suspend fun execute(): List<UserPositionResponseData> {
        return fireStoreRepository.fetchUserLocations()
    }

}