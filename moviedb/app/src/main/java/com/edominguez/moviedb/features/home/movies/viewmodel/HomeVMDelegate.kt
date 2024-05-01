package com.edominguez.moviedb.features.home.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edominguez.moviedb.core.base.BaseVMDelegate
import com.edominguez.moviedb.features.home.movies.datasource.model.MoviesResponseData

class HomeVMDelegate: BaseVMDelegate() {

    private val _onListMoviesResponse = MutableLiveData<MoviesResponseData>()
    val onListMovieResponse: LiveData<MoviesResponseData> get() = _onListMoviesResponse
    fun onListMovieResponsePostValue(data: MoviesResponseData) {
        _onListMoviesResponse.postValue(data)
    }

    private val _onLocationSavedResponse = MutableLiveData<MoviesResponseData>()
    val onLocationSavedResponse: LiveData<MoviesResponseData> get() = _onLocationSavedResponse
    fun onLocationSavedResponsePostValue(data: MoviesResponseData) {
        _onLocationSavedResponse.postValue(data)
    }
}