package com.edominguez.moviedb.core.common.components

import org.koin.core.module.Module
import org.koin.dsl.module

val animationModule : Module = module {
    single { Animation() }
}

