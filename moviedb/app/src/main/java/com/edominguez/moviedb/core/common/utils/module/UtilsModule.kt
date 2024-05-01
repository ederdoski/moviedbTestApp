package com.edominguez.moviedb.core.common.utils.module

import android.content.Context
import com.edominguez.moviedb.core.common.utils.MapsDelegate
import com.edominguez.moviedb.core.common.utils.NotificationDelegate
import org.koin.core.module.Module
import org.koin.dsl.module

val utilsModule: Module = module {
    single {
        providerMapsDelegate()
    }

    single {
        providerNotificationDelegate(context = get())
    }
}

fun providerMapsDelegate(): MapsDelegate {
    return MapsDelegate()
}

fun providerNotificationDelegate(context: Context): NotificationDelegate {
    return NotificationDelegate(context)
}

