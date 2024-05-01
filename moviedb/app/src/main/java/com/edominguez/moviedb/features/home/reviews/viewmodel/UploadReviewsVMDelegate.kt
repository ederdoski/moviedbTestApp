package com.edominguez.moviedb.features.home.reviews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edominguez.moviedb.core.base.BaseVMDelegate

class UploadReviewsVMDelegate : BaseVMDelegate() {

    private val _onReviewUploadedResponse = MutableLiveData<Boolean>()
    val onReviewUploadedResponse: LiveData<Boolean> get() = _onReviewUploadedResponse
    fun onReviewUploadedResponsePostValue(isSaved:Boolean) {
        _onReviewUploadedResponse.postValue(isSaved)
    }
}