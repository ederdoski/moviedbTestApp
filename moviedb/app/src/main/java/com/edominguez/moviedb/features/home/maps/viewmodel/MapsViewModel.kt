package com.edominguez.moviedb.features.home.maps.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edominguez.moviedb.features.home.maps.usecase.MapsUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class MapsViewModel(
    private val fireStoreUseCase: MapsUseCase,
    val fireStoreVMDelegate: MapsVMDelegate
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