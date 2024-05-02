package com.edominguez.moviedb.features.home.profile.module

import com.edominguez.moviedb.features.home.profile.datasource.repository.ProfileRepository
import com.edominguez.moviedb.features.home.profile.datasource.service.ProfileService
import com.edominguez.moviedb.features.home.profile.usecase.ProfileUseCase
import com.edominguez.moviedb.features.home.profile.viewmodel.ProfileVMDelegate
import com.edominguez.moviedb.features.home.profile.viewmodel.ProfileViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val profileModule: Module = module {

    //--- Inject viewModel
    viewModel {
        ProfileViewModel(
            profileUseCase = get(),
            profileVMDelegate = get()
        )
    }

    //--- Inject repository
    single<ProfileRepository> {
        ProfileRepository(
            profileService = get()
        )
    }

    //--- Inject useCase
    single { providerProfileUseCase(get()) }

    //--- Inject service
    single { providerProfileService() }

    factory { providerProfileVMDelegate() }
}

fun providerProfileVMDelegate(): ProfileVMDelegate {
    return ProfileVMDelegate()
}

fun providerProfileService(): ProfileService {
    return ProfileService(Firebase.firestore)
}

fun providerProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase {
    return ProfileUseCase(profileRepository)
}