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
    private lateinit var userPositionRequestData: UserPositionRequestData

    fun bindMovie(page: Int, filter:String) {
        movieRequestData =
            MoviesRequestData(
                page,
                filter
            )
    }

    fun bindUserPosition(deviceID: String, latLng: LatLng) {
        userPositionRequestData =
            UserPositionRequestData(
                latLng,
                deviceID,
                LocalDate.now()
            )
    }

    suspend fun getMovies(): Response<MoviesResponseData> =
        homeRepository.getMovies(movieRequestData)

    suspend fun getMorePopularUser(): Response<MoviesResponseData> =
        homeRepository.getMorePopularUser()

    fun setSession(token: String) = homeRepository.setSession(token)

}
