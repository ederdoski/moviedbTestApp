package com.edominguez.moviedb.core.preferences

import android.app.Application
import org.koin.core.module.Module
import org.koin.dsl.module

val preferencesModule: Module = module {
    single { providerPreferences(context = get()) }
}

fun providerPreferences(context: Application): Preferences {
    return Preferences(context)
}
