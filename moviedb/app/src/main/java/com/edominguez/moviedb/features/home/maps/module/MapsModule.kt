package com.edominguez.moviedb.features.home.maps.module

import com.edominguez.moviedb.features.home.maps.datasource.repository.MapsRepository
import com.edominguez.moviedb.features.home.maps.datasource.service.MapsService
import com.edominguez.moviedb.features.home.maps.usecase.MapsUseCase
import com.edominguez.moviedb.features.home.maps.viewmodel.MapsVMDelegate
import com.edominguez.moviedb.features.home.maps.viewmodel.MapsViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val fireStoreModule: Module = module {

    //--- Inject viewModel
    viewModel {
        MapsViewModel(
            fireStoreUseCase = get(),
            fireStoreVMDelegate = get()
        )
    }
    //--- Inject repository
    single<MapsRepository> {
        MapsRepository(
            fireStoreService = get()
        )
    }

    //--- Inject useCase
    single { providerFireStoreUseCase(get()) }

    //--- Inject service
    single { providerFireStoreService() }

    factory { providerFireStoreVMDelegate() }
}

fun providerFireStoreVMDelegate(): MapsVMDelegate {
    return MapsVMDelegate()
}

fun providerFireStoreService(): MapsService {
    return MapsService(Firebase.firestore)
}

fun providerFireStoreUseCase(fireStoreRepository: MapsRepository): MapsUseCase {
    return MapsUseCase(fireStoreRepository)
}
