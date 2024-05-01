package com.edominguez.moviedb.features.home.maps.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreCollections
import com.edominguez.moviedb.features.home.maps.usecase.FireStoreUseCase
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.joda.time.LocalDateTime

class FireStoreViewModel(
    private val fireStoreUseCase: FireStoreUseCase,
    val fireStoreVMDelegate: FireStoreVMDelegate
) : ViewModel() {

    /** Maps Services **/

    fun getUserLocations() {
        viewModelScope.launch(fireStoreVMDelegate.exceptionHandler()) {
            try {
                val locations = fireStoreUseCase.getUserLocations()
                fireStoreVMDelegate.onUsersLocationResponsePostValue(locations)
            } catch (exception: Exception) {
                fireStoreVMDelegate.onUsersLocationResponseFailedPostValue()
            }
        }
    }

    fun saveOrUpdateNewLocation(deviceID: String, latLng: LatLng) {
        viewModelScope.launch {
            val isSaved = fireStoreUseCase.saveOrUpdateNewLocation(deviceID, latLng)
            fireStoreVMDelegate.onUserLocationSavedResponsePostValue(isSaved)
        }
    }

}