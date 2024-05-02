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


val mapsModule: Module = module {

    //--- Inject viewModel
    viewModel {
        MapsViewModel(
            mapsUseCase = get(),
            mapsVMDelegate = get()
        )
    }
    //--- Inject repository
    single<MapsRepository> {
        MapsRepository(
            mapsService = get()
        )
    }

    //--- Inject useCase
    single { providerMapsUseCase(get()) }

    //--- Inject service
    single { providerMapsService() }

    factory { providerMapsMDelegate() }
}

fun providerMapsMDelegate(): MapsVMDelegate {
    return MapsVMDelegate()
}

fun providerMapsService(): MapsService {
    return MapsService(Firebase.firestore)
}

fun providerMapsUseCase(mapsRepository: MapsRepository): MapsUseCase {
    return MapsUseCase(mapsRepository)
}
