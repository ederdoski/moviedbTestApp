package com.edominguez.moviedb.features.home.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edominguez.moviedb.BuildConfig
import com.edominguez.moviedb.core.common.utils.FIRST_PAGE
import com.edominguez.moviedb.core.network.NetworkResponse
import com.edominguez.moviedb.features.home.movies.usecase.HomeUseCase
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

class HomeViewModel (
    private val homeUseCase: HomeUseCase,
    val homeVMDelegate: HomeVMDelegate,
) : ViewModel() {

    /** Movies Services **/

    fun getMovies(filter:String) {
        viewModelScope.launch (homeVMDelegate.exceptionHandler()) {
            homeVMDelegate.loadingPostValue(true)
            homeUseCase.bindMovie(FIRST_PAGE, filter)
            val response = NetworkResponse(homeUseCase.getMovies())
            when (response.network.httpCode) {
                HttpURLConnection.HTTP_OK -> {
                    homeVMDelegate.onListMovieResponsePostValue(response.data!!)
                }
                else -> {
                    homeVMDelegate.showUnknownErrorPostValue(Throwable(response.network.message))
                }
            }
        }
    }

    fun setSession() {
        homeUseCase.setSession(BuildConfig.SESSION_TOKEN)
    }
}
