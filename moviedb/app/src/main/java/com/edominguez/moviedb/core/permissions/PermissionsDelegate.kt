package com.edominguez.moviedb.core.permissions

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.fragment.app.Fragment
import com.edominguez.moviedb.core.preferences.Preferences
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.isGranted
import com.fondesa.kpermissions.request.PermissionRequest
import org.koin.core.component.KoinComponent

class PermissionsDelegate(val context: Context) : KoinComponent {

    private fun checkPermissionStatus(requestPermission: PermissionRequest): Boolean {
        val permissionsStatus = requestPermission.checkStatus()
        var allPermissionGranted = true
        permissionsStatus.map {
            allPermissionGranted = it.isGranted()
        }
        return allPermissionGranted
    }

    fun requestPushNotificationPermissions(fragment: Fragment, event: PermissionHelperEvents) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val requestPermission =
                fragment.permissionsBuilder(Manifest.permission.POST_NOTIFICATIONS).build()
            requestPermission.addListener { result ->
                var allPermissionGranted = true
                result.map {
                    allPermissionGranted = it.isGranted()
                }
                if (allPermissionGranted) {
                    event.onSuccessPermissionsGranted()
                } else {
                    event.onDeniedPermissions()
                }
            }
            requestPermission.send()
        }else{
            event.onSuccessPermissionsGranted()
        }
    }

}