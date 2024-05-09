package com.edominguez.moviedb.features.home.profile.datasource.repository

import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreFields
import com.edominguez.moviedb.features.home.maps.datasource.model.UserPositionResponseData
import com.edominguez.moviedb.features.home.profile.datasource.model.ReviewsResponseData
import com.edominguez.moviedb.features.home.profile.datasource.service.ProfileService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class ProfileRepository(private val profileService: ProfileService) {

    suspend fun getUserReviews(): List<ReviewsResponseData> {
        val task = profileService.getUserReviews()
        val result = task.await()

        return result.documents.mapNotNull { document ->
            try {
                val randomID = Random.nextInt(100)
                val path = document.getString(FireStoreFields.PATH) ?: return@mapNotNull null
                val rating = document.getDouble(FireStoreFields.RATING) ?: return@mapNotNull null
                val comment = document.getString(FireStoreFields.COMMENT) ?: return@mapNotNull null
                ReviewsResponseData(randomID, path, rating.toFloat(), comment)
            } catch (e: Exception) {
                null
            }
        }
    }
}