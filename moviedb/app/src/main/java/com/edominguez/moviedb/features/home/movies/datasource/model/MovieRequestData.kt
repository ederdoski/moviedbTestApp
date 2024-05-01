package com.edominguez.moviedb.features.home.movies.datasource.model

import com.google.gson.annotations.SerializedName

data class MoviesRequestData (
    @SerializedName("page") val page: Int,
    @SerializedName("filter") val filter: String
)