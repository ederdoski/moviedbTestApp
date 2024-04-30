package com.edominguez.moviedb.features.home.usecase

import com.edominguez.moviedb.features.home.datasource.model.MoviesRequestData
import com.edominguez.moviedb.features.home.datasource.model.MoviesResponseData
import com.edominguez.moviedb.features.home.datasource.repository.HomeRepository
import retrofit2.Response

class HomeUseCase (private val homeRepository: HomeRepository) {

    private lateinit var movieRequestData: MoviesRequestData

    fun bindMovie(page: Int, filter:String) {
        movieRequestData = MoviesRequestData(page, filter)
    }
    suspend fun getMovies(): Response<MoviesResponseData> =
        homeRepository.getMovies(movieRequestData)

    suspend fun getMorePopularUser(): Response<MoviesResponseData> =
        homeRepository.getMorePopularUser()

    fun setSession(token: String) = homeRepository.setSession(token)

}
