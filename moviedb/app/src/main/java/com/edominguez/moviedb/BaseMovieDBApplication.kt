package com.edominguez.moviedb

import android.app.Application
import com.edominguez.moviedb.core.common.components.animationModule
import com.edominguez.moviedb.core.common.utils.module.utilsModule
import com.edominguez.moviedb.core.network.networkModule
import com.edominguez.moviedb.core.preferences.preferencesModule
import com.edominguez.moviedb.features.home.movies.module.homeModule
import com.edominguez.moviedb.core.permissions.module.permissionsModule
import com.edominguez.moviedb.features.home.maps.module.mapsModule
import com.edominguez.moviedb.features.home.profile.module.profileModule
import com.edominguez.moviedb.features.home.reviews.module.uploadReviewsModule
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
                    utilsModule,
                    homeModule,
                    mapsModule,
                    networkModule,
                    profileModule,
                    animationModule,
                    permissionsModule,
                    preferencesModule,
                    uploadReviewsModule
                )
            )
        }

    }

}
