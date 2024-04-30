package com.edominguez.moviedb

import android.app.Application
import com.edominguez.moviedb.core.common.components.animationModule
import com.edominguez.moviedb.core.network.networkModule
import com.edominguez.moviedb.core.preferences.preferencesModule
import com.edominguez.moviedb.features.home.module.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class BaseMovieDBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.INFO)
            androidContext(this@BaseMovieDBApplication)
            koin.loadModules(
                listOf(
                    networkModule,
                    animationModule,
                    preferencesModule,
                    homeModule
                )
            )
        }

    }

}
