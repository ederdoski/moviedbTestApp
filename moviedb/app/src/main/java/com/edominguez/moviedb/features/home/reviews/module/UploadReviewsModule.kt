package com.edominguez.moviedb.features.home.reviews.module

import com.edominguez.moviedb.features.home.reviews.datasource.repository.UploadReviewsRepository
import com.edominguez.moviedb.features.home.reviews.datasource.service.UploadReviewsService
import com.edominguez.moviedb.features.home.reviews.usecase.UploadReviewsUseCase
import com.edominguez.moviedb.features.home.reviews.viewmodel.UploadReviewsVMDelegate
import com.edominguez.moviedb.features.home.reviews.viewmodel.UploadReviewsViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val uploadReviewsModule: Module = module {

    //--- Inject viewModel
    viewModel {
        UploadReviewsViewModel(
            uploadReviewsUseCase = get(),
            uploadReviewsVMDelegate = get()
        )
    }
    //--- Inject repository
    single<UploadReviewsRepository> {
        UploadReviewsRepository(
            uploadReviewsService = get()
        )
    }

    //--- Inject useCase
    single { providerUploadReviewUseCase(get()) }

    //--- Inject service
    single { providerUploadReviewsService() }

    factory { providerUploadReviewVMDelegate() }
}

fun providerUploadReviewVMDelegate(): UploadReviewsVMDelegate {
    return UploadReviewsVMDelegate()
}

fun providerUploadReviewsService(): UploadReviewsService {
    return UploadReviewsService(Firebase.firestore, FirebaseStorage.getInstance())
}

fun providerUploadReviewUseCase(uploadReviewRepository: UploadReviewsRepository): UploadReviewsUseCase {
    return UploadReviewsUseCase(uploadReviewRepository)
}

