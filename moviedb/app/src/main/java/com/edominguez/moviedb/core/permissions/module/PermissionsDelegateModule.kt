package com.edominguez.moviedb.core.permissions.module

import android.content.Context
import com.edominguez.moviedb.core.permissions.PermissionsDelegate
import org.koin.core.module.Module
import org.koin.dsl.module

val permissionsModule : Module = module {
    single {
        providerPermissionsDelegate(context = get())
    }
}

fun providerPermissionsDelegate(context: Context): PermissionsDelegate {
    return PermissionsDelegate(context)
}