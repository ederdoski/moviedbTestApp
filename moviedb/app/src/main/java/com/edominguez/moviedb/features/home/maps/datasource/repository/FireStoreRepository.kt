package com.edominguez.moviedb.features.home.maps.datasource.repository

import android.util.Log
import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreFields
import com.edominguez.moviedb.features.home.maps.datasource.model.UserPositionResponseData
import com.edominguez.moviedb.features.home.maps.datasource.service.FireStoreService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await
import org.joda.time.LocalDateTime

class FireStoreRepository(private val fireStoreService: FireStoreService) {

    suspend fun getUserLocations(): List<UserPositionResponseData> {
        val task = fireStoreService.getUserLocations()
        val result = task.await()

        return result.documents.mapNotNull { document ->
            try {
                val id =  document.id
                val latitude = document.getString(FireStoreFields.LATITUDE) ?: return@mapNotNull null
                val longitude = document.getString(FireStoreFields.LONGITUDE) ?: return@mapNotNull null
                val createdAt = document.getString(FireStoreFields.CREATED_AT) ?: return@mapNotNull null
                val latLng = LatLng(latitude.toDouble(), longitude.toDouble())
                UserPositionResponseData(id, latLng, createdAt)
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun saveOrUpdateNewLocation(deviceID: String, latLng: LatLng, createdAt:String): Boolean {
        val task = fireStoreService.saveOrUpdateUserPosition(deviceID, latLng, createdAt)

        return try {
            task.await()
            true
        } catch (e: Exception) {
            false
        }
    }
}