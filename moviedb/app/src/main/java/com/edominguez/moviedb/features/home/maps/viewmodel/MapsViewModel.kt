package com.edominguez.moviedb.features.home.maps.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edominguez.moviedb.features.home.maps.usecase.MapsUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class MapsViewModel(
    private val mapsUseCase: MapsUseCase,
    val mapsVMDelegate: MapsVMDelegate
) : ViewModel() {

    /** Maps Services **/

    fun getUserLocations() {
        viewModelScope.launch(mapsVMDelegate.exceptionHandler()) {
            try {
                val locations = mapsUseCase.getUserLocations()
                mapsVMDelegate.onUsersLocationResponsePostValue(locations)
            } catch (exception: Exception) {
                mapsVMDelegate.onUsersLocationResponseFailedPostValue()
            }
        }
    }

    fun saveOrUpdateNewLocation(deviceID: String, latLng: LatLng) {
        viewModelScope.launch {
            val isSaved = mapsUseCase.saveOrUpdateNewLocation(deviceID, latLng)
            mapsVMDelegate.onUserLocationSavedResponsePostValue(isSaved)
        }
    }

}