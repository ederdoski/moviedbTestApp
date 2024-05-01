package com.edominguez.moviedb.features.home.movies.datasource.repository

import com.edominguez.moviedb.core.preferences.Preferences
import com.edominguez.moviedb.features.home.movies.datasource.model.MoviesRequestData
import com.edominguez.moviedb.features.home.movies.datasource.model.MoviesResponseData
import com.edominguez.moviedb.features.home.movies.datasource.service.HomeService
import retrofit2.Response

class HomeRepository (
    private val homeService: HomeService,
    private val preferences: Preferences
) {
    suspend fun getMovies(movieRequestData: MoviesRequestData): Response<MoviesResponseData> = homeService.getMovies(movieRequestData.filter, movieRequestData.page)
    suspend fun getMorePopularUser(): Response<MoviesResponseData> = homeService.getMorePopularUser()

    fun setSession(token: String) {
        preferences.jwt = token
    }
}
