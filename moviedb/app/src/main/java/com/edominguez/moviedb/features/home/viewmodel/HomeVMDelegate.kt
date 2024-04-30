package com.edominguez.moviedb.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edominguez.moviedb.core.base.BaseVMDelegate
import com.edominguez.moviedb.features.home.datasource.model.MoviesResponseData

class HomeVMDelegate: BaseVMDelegate() {

    private val _onListMoviesResponse = MutableLiveData<MoviesResponseData>()
    val onListMovieResponse: LiveData<MoviesResponseData> get() = _onListMoviesResponse
    fun onListMovieResponsePostValue(data: MoviesResponseData) {
        _onListMoviesResponse.postValue(data)
    }

    private val _onMorePopularUserResponse = MutableLiveData<MoviesResponseData>()
    val onMorePopularUserResponse: LiveData<MoviesResponseData> get() = _onMorePopularUserResponse
    fun onMorePopularUserResponsePostValue(data: MoviesResponseData) {
        _onMorePopularUserResponse.postValue(data)
    }
}