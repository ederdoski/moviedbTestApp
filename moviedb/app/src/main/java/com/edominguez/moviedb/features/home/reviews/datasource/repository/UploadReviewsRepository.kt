package com.edominguez.moviedb.features.home.reviews.datasource.repository

import com.edominguez.moviedb.features.home.reviews.datasource.model.UploadReviewsRequestData
import com.edominguez.moviedb.features.home.reviews.datasource.service.UploadReviewsService
import kotlinx.coroutines.tasks.await

class UploadReviewsRepository(private val uploadReviewsService: UploadReviewsService) {

    suspend fun uploadReview(uploadReviewsRequestData: UploadReviewsRequestData): Boolean {
        val task = uploadReviewsService.uploadReview(uploadReviewsRequestData)

        return try {
            task.await()
            true
        } catch (e: Exception) {
            false
        }
    }
}