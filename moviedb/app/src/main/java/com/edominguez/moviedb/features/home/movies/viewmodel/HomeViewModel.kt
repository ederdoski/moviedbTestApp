package com.edominguez.moviedb.features.home.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edominguez.moviedb.BuildConfig
import com.edominguez.moviedb.BuildConfig.TMDB_TOKEN
import com.edominguez.moviedb.core.common.utils.FIRST_PAGE
import com.edominguez.moviedb.core.network.NetworkResponse
import com.edominguez.moviedb.features.home.movies.usecase.HomeUseCase
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

class HomeViewModel (
    private val homeUseCase: HomeUseCase,
    val homeVMDelegate: HomeVMDelegate,
) : ViewModel() {

    /** Movies Services **/

    private var movieListActPage = 1
    private var movieListMaxPage: Boolean = false

    fun getMovies(filter:String, enablePagination: Boolean = true) {
        viewModelScope.launch (homeVMDelegate.exceptionHandler()) {
            homeVMDelegate.loadingPostValue(true)
            homeUseCase.bindMovie(movieListActPage, filter)
            val response = NetworkResponse(homeUseCase.getMovies())
            when (response.network.httpCode) {
                HttpURLConnection.HTTP_OK -> {
                    if (movieListActPage == response.data!!.totalPages) {
                        movieListMaxPage = true
                    }else{
                        if (enablePagination) movieListActPage++
                    }
                    homeVMDelegate.onListMovieResponsePostValue(response.data!!)
                }
                else -> {
                    homeVMDelegate.showUnknownErrorPostValue(Throwable(response.network.message))
                }
            }
        }
    }

    fun setSession() {
        homeUseCase.setSession(TMDB_TOKEN)
    }
}
