package com.edominguez.moviedb.features.home.profile.datasource.model

data class ReviewsResponseData (
    val id:Int,
    val path: String,
    val rating: Float,
    val comment: String
)
