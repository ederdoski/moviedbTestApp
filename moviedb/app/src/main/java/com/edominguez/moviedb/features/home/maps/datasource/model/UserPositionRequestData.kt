package com.edominguez.moviedb.features.home.maps.datasource.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDate

data class UserPositionRequestData(
    @SerializedName("latLng") val latLng: LatLng,
    @SerializedName("deviceID") val deviceID: String,
    @SerializedName("createdAt") val createdAt: LocalDate
)