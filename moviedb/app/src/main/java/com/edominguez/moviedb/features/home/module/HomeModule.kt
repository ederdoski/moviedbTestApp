package com.edominguez.moviedb.features.home.module

import com.edominguez.moviedb.core.network.API_AUTH
import com.edominguez.moviedb.core.network.API_WITHOUT_AUTH
import com.edominguez.moviedb.features.home.datasource.repository.HomeRepository
import com.edominguez.moviedb.features.home.datasource.service.HomeService
import com.edominguez.moviedb.features.home.usecase.HomeUseCase
import com.edominguez.moviedb.features.home.viewmodel.HomeVMDelegate
import com.edominguez.moviedb.features.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


val homeModule: Module = module {

    //--- Inject viewModel
    viewModel {
        HomeViewModel(
            homeUseCase = get(),
            homeVMDelegate = get()
        )
    }
    //--- Inject repository
    single<HomeRepository> {
        HomeRepository(
            homeService = get(),
            preferences = get()
        )
    }

    //--- Inject useCase
    single { providerHomeUseCase(get()) }

    //--- Inject service
    single { providerHomeService(get(named(API_AUTH))) }
    factory { providerHomeVMDelegate() }
}

fun providerHomeVMDelegate(): HomeVMDelegate {
    return HomeVMDelegate()
}

fun providerHomeService(retrofit: Retrofit): HomeService {
    return retrofit.create(HomeService::class.java)
}

fun providerHomeUseCase(loginRepository: HomeRepository): HomeUseCase {
    return HomeUseCase(loginRepository)
}

