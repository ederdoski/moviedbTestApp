package com.edominguez.moviedb.features.home.reviews.datasource.model

import android.net.Uri

data class UploadReviewsRequestData(
    val path: Uri,
    val rating: Float,
    val comment: String,
    val deviceID: String
)