package com.edominguez.moviedb.main.module


import com.edominguez.moviedb.core.network.API_WITHOUT_AUTH
import com.edominguez.moviedb.main.datasource.repository.MainRepository
import com.edominguez.moviedb.main.datasource.service.MainService
import com.edominguez.moviedb.main.usecase.MainUseCase
import com.edominguez.moviedb.main.viewmodel.MainVMDelegate
import com.edominguez.moviedb.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule : Module =  module {

    //--- Inject viewModel
    viewModel {
        MainViewModel(
          mainUseCase = get(),
          mainVMDelegate = get()
        )
    }

    //--- Inject repository
    single<MainRepository> {
      MainRepository()
    }

    //--- Inject useCase
    single { providerMainUseCase(get()) }

    //--- Inject service
    single { providerMainService(get(named(API_WITHOUT_AUTH))) }

    single { providerMainVMDelegate() }

}

fun providerMainService(retrofit: Retrofit): MainService {
    return retrofit.create(MainService::class.java)
}

fun providerMainUseCase(mainRepository: MainRepository): MainUseCase {
    return MainUseCase(mainRepository)
}

fun providerMainVMDelegate(): MainVMDelegate {
    return MainVMDelegate()
}