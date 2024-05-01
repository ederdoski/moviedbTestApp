package com.edominguez.moviedb.core.common.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.edominguez.moviedb.features.home.maps.datasource.model.MapsMarkersViewInput
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MapsDelegate {

    private lateinit var googleMap : GoogleMap
    private var aMarks : ArrayList<MapsMarkersViewInput> = arrayListOf()

    companion object {
        const val PERMISSIONS_REQUEST_ACCESS_LOCATION = 100
    }

    interface MapsListener {
        fun onMarkerClicked(clickedMarker: MapsMarkersViewInput)
        fun onNeedToSaveNewPosition(latLng: LatLng)
        fun onLocationObtained(location: Location)
        fun locationUnknown()
    }

    fun checkLocationPermissionEnabled(context: Context): Boolean {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }

    fun checkGpsEnabled(activity: Activity): Boolean {
        val manager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return manager.isProviderEnabled( LocationManager.GPS_PROVIDER )
    }

    fun startFlowWhenLocationPermissionsDisabled(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSIONS_REQUEST_ACCESS_LOCATION
        )
    }

    fun goToGPSSettings(activity: Activity) {
        activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    fun isPermissionGPSValid(requestCode:Int, grantResults:IntArray):Boolean {
        if(requestCode == PERMISSIONS_REQUEST_ACCESS_LOCATION) {
            return grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    fun startFlowWhenLocationPermissionsEnabled(activity: Activity, mapsListener: MapsListener) {
        getCurrentLocation(
            activity = activity,
            callback = { location ->
                if(location != null) {
                    mapsListener.onLocationObtained(location)
                }else{
                    mapsListener.locationUnknown()
                }
            }
        )
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(activity: Activity, callback: (location: Location?) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(
            activity
        )
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                if(it != null) {
                    Log.e("Lat location", it.latitude.toString() + "--"+ it.longitude)
                    callback(it)
                }else {
                    recoverLocation(fusedLocationClient, callback)
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun recoverLocation(
        fusedLocationClient: FusedLocationProviderClient,
        callback: (location: Location?) -> Unit
    ) {
        val locationCallback: LocationCallback
        val locationRequest = LocationRequest()

        locationRequest.interval = 50000
        locationRequest.fastestInterval = 50000
        locationRequest.smallestDisplacement = 170f
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(places: LocationResult) {
                for (location in places.locations) {
                    callback(location)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    fun updateUserPosition(latLng: LatLng, mapsListener: MapsListener) {
        val scheduler = Executors.newScheduledThreadPool(1)

        scheduler.scheduleAtFixedRate({
            mapsListener.onNeedToSaveNewPosition(latLng)
        }, 0, 1, TimeUnit.MINUTES)
    }

    fun addMarkerToMap(id: String, latLng: LatLng, createdAt: String) {
        aMarks.add(
            MapsMarkersViewInput(
                id = id,
                latLng = latLng,
                createdAt = createdAt,
                marker = getGoogleMap().addMarker(
                    MarkerOptions()
                        .position(latLng)
                )
            )
        )

        getGoogleMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
    }

    fun checkClicksInMarker(mapsListener: MapsListener) {
        getGoogleMap().setOnMarkerClickListener { marker ->
            val clickedMarker = aMarks.find { it.marker == marker }
            mapsListener.onMarkerClicked(clickedMarker!!)
            true
        }
    }

    fun resetMap() {
        aMarks.clear()
        getGoogleMap().clear()
    }

    fun setGoogleMap(_googleMap: GoogleMap) {
        googleMap = _googleMap
    }

    fun getGoogleMap(): GoogleMap {
        return googleMap
    }
}
