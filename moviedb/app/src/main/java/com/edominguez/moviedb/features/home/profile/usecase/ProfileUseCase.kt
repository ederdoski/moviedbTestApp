package com.edominguez.moviedb.features.home.profile.usecase

import com.edominguez.moviedb.features.home.profile.datasource.model.ReviewsResponseData
import com.edominguez.moviedb.features.home.profile.datasource.repository.ProfileRepository

class ProfileUseCase(private val profileRepository: ProfileRepository) {

    suspend fun getUserReviews(): List<ReviewsResponseData> {
        return profileRepository.getUserReviews()
    }

}