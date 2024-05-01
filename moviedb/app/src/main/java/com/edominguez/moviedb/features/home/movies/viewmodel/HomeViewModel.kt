package com.edominguez.moviedb.features.home.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        viewModelScope.launch (homeVMDelegate.exceptionHandler()){
            homeVMDelegate.loadingPostValue(true)
            homeUseCase.bindMovie(1, filter)
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

    fun getMorePopularUser() {
        viewModelScope.launch (homeVMDelegate.exceptionHandler()){
            homeVMDelegate.loadingPostValue(true)
            val response = NetworkResponse(homeUseCase.getMorePopularUser())
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
        homeUseCase.setSession("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiOWQ4ZWY3YmU2ZGJlZjFhYTE5YjNjMjZiYmJkYWJhZSIsInN1YiI6IjY2MzEwY2NiNDgzMzNhMDEyMTkxYjNjYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.eIreqtQobAXRhFtoJq624imOEbWNf4xNH2LYnfH0wpg")
    }
}
