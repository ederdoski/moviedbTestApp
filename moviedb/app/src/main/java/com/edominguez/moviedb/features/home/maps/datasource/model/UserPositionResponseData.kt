package com.edominguez.moviedb.features.home.maps.datasource.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class UserPositionResponseData(
    @SerializedName("id") val id: String,
    @SerializedName("position") val latLng: LatLng,
    @SerializedName("created_at") val createdAt: String
)