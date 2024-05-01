package com.edominguez.moviedb.features.home.reviews.usecase

import android.net.Uri
import com.edominguez.moviedb.features.home.reviews.datasource.model.UploadReviewsRequestData
import com.edominguez.moviedb.features.home.reviews.datasource.repository.UploadReviewsRepository

class UploadReviewsUseCase(private val uploadReviewsRepository: UploadReviewsRepository) {

    private lateinit var uploadReviewsRequestData: UploadReviewsRequestData

    fun bindReviewData(deviceID:String, comment:String, rating:Float, path:Uri) {
        uploadReviewsRequestData =
            UploadReviewsRequestData(
                path = path,
                rating = rating,
                comment = comment,
                deviceID = deviceID
            )
    }
    suspend fun uploadReview(): Boolean {
        return uploadReviewsRepository.uploadReview(uploadReviewsRequestData)
    }

}