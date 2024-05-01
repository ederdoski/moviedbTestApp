package com.edominguez.moviedb.features.home.maps.datasource.model

import com.google.android.gms.tasks.Task

object FireStoreFields {
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val CREATED_AT = "created_at"

    const val PATH = "path"
    const val RATING = "rating"
    const val COMMENT = "comment"
}


object FireStoreCollections {
    const val USERS = "Users"
    const val REVIEWS = "Reviews"
}