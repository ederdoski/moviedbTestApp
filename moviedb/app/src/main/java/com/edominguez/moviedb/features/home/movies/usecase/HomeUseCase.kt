package com.edominguez.moviedb.features.home.movies.usecase

import com.edominguez.moviedb.features.home.maps.datasource.model.UserPositionRequestData
import com.edominguez.moviedb.features.home.movies.datasource.model.MoviesRequestData
import com.edominguez.moviedb.features.home.movies.datasource.model.MoviesResponseData
import com.edominguez.moviedb.features.home.movies.datasource.repository.HomeRepository
import com.google.android.gms.maps.model.LatLng
import org.joda.time.LocalDate
import retrofit2.Response

class HomeUseCase (private val homeRepository: HomeRepository) {

    private lateinit var movieRequestData: MoviesRequestData

    fun bindMovie(page: Int, filter:String) {
        movieRequestData =
            MoviesRequestData(
                page,
                filter
            )
    }

    suspend fun getMovies(): Response<MoviesResponseData> =
        homeRepository.getMovies(movieRequestData)

    fun setSession(token: String) = homeRepository.setSession(token)

}
