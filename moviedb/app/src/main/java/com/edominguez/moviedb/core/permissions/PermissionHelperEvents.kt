package com.edominguez.moviedb.core.permissions

interface PermissionHelperEvents {
    fun onSuccessPermissionsGranted()
    fun onDeniedPermissions()
}