package com.edominguez.moviedb.features.home.maps.module

import com.edominguez.moviedb.features.home.maps.datasource.repository.FireStoreRepository
import com.edominguez.moviedb.features.home.maps.datasource.service.FireStoreService
import com.edominguez.moviedb.features.home.maps.usecase.FireStoreUseCase
import com.edominguez.moviedb.features.home.maps.viewmodel.FireStoreVMDelegate
import com.edominguez.moviedb.features.home.maps.viewmodel.FireStoreViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val fireStoreModule: Module = module {

    //--- Inject viewModel
    viewModel {
        FireStoreViewModel(
            fireStoreUseCase = get(),
            fireStoreVMDelegate = get()
        )
    }
    //--- Inject repository
    single<FireStoreRepository> {
        FireStoreRepository(
            fireStoreService = get()
        )
    }

    //--- Inject useCase
    single { providerFireStoreUseCase(get()) }

    //--- Inject service
    single { providerFireStoreService() }

    factory { providerFireStoreVMDelegate() }
}

fun providerFireStoreVMDelegate(): FireStoreVMDelegate {
    return FireStoreVMDelegate()
}

fun providerFireStoreService(): FireStoreService {
    return FireStoreService(Firebase.firestore)
}

fun providerFireStoreUseCase(fireStoreRepository: FireStoreRepository): FireStoreUseCase {
    return FireStoreUseCase(fireStoreRepository)
}
