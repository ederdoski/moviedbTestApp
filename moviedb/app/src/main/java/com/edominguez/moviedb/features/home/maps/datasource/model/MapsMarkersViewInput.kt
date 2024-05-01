package com.edominguez.moviedb.features.home.maps.datasource.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

data class MapsMarkersViewInput (
    val id: String,
    val latLng: LatLng,
    val marker: Marker?,
    val createdAt: String,
)