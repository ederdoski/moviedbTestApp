package com.edominguez.moviedb.features.home.reviews.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edominguez.moviedb.core.common.utils.EMPTY_STRING
import com.edominguez.moviedb.features.home.reviews.usecase.UploadReviewsUseCase
import kotlinx.coroutines.launch

class UploadReviewsViewModel (
    private val uploadReviewsUseCase: UploadReviewsUseCase,
    val uploadReviewsVMDelegate: UploadReviewsVMDelegate,
) : ViewModel() {

    private var imgToAttachPath:Uri? = null

    /** Review Services **/

    fun uploadReview(deviceID:String, comment:String, rating:Float) {
        viewModelScope.launch {
            uploadReviewsUseCase.bindReviewData(deviceID, comment, rating, getImagePath()!!)
            val isSaved = uploadReviewsUseCase.uploadReview()
            uploadReviewsVMDelegate.onReviewUploadedResponsePostValue(isSaved)
        }
    }

    fun setImagePath(path: Uri?) {
        imgToAttachPath = path
    }

    fun getImagePath():Uri? {
        return imgToAttachPath
    }
}