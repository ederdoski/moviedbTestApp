package com.edominguez.moviedb.features.home.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edominguez.moviedb.core.base.BaseVMDelegate
import com.edominguez.moviedb.features.home.movies.datasource.model.MoviesResponseData
import com.edominguez.moviedb.features.home.profile.datasource.model.ReviewsResponseData

class ProfileVMDelegate : BaseVMDelegate() {

    private val _onListReviewsResponse = MutableLiveData<List<ReviewsResponseData>>()
    val onListReviewsResponse: LiveData<List<ReviewsResponseData>> get() = _onListReviewsResponse
    fun onListReviewsResponsePostValue(data: List<ReviewsResponseData>) {
        _onListReviewsResponse.postValue(data)
    }

    private val _onListReviewsResponseFailed = MutableLiveData<Unit>()
    val onListReviewsResponseFailed: LiveData<Unit> get() = _onListReviewsResponseFailed
    fun onListReviewsResponseFailedPostValue() {
        _onListReviewsResponseFailed.postValue(Unit)
    }

}