package com.edominguez.moviedb.core.common.utils.module

import com.edominguez.moviedb.core.common.utils.MapsDelegate
import org.koin.core.module.Module
import org.koin.dsl.module

val utilsModule: Module = module {
    single {
        providerMapsDelegate()
    }
}

fun providerMapsDelegate(): MapsDelegate {
    return MapsDelegate()
}

