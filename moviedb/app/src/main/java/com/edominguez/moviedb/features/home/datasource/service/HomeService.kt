package com.edominguez.moviedb.features.home.datasource.service

import com.edominguez.moviedb.features.home.datasource.model.MoviesResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET("/3/movie/{filter}")
    suspend fun getMovies(@Path("filter") filter: String, @Query("page") page:Int): Response<MoviesResponseData>

    @GET("3/person/popular")
    suspend fun getMorePopularUser(): Response<MoviesResponseData>

}