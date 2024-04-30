package com.edominguez.moviedb.features.home.datasource.model

import com.google.gson.annotations.SerializedName

data class MoviesResponseData (
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val results: ArrayList<MovieData>
)

data class MovieData (
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("original_title") val originalTitle: String
)