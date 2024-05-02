package com.edominguez.moviedb.features.home.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edominguez.moviedb.features.home.profile.usecase.ProfileUseCase
import kotlinx.coroutines.launch

class ProfileViewModel (
    private val profileUseCase: ProfileUseCase,
    val profileVMDelegate: ProfileVMDelegate
) : ViewModel() {

    /** Profile Services **/
    fun getUserReviews() {
        viewModelScope.launch(profileVMDelegate.exceptionHandler()) {
            profileVMDelegate.loadingPostValue(true)
            try {
                val reviews = profileUseCase.getUserReviews()
                profileVMDelegate.onListReviewsResponsePostValue(reviews)
            } catch (exception: Exception) {
                profileVMDelegate.onListReviewsResponseFailedPostValue()
            }
        }
    }

}