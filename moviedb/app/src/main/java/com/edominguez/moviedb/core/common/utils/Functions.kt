package com.edominguez.moviedb.core.common.utils


import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.graphics.Insets
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.Html
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.network.PLATFORM
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.io.File
import java.util.*


class Functions {

    companion object {

        @SuppressLint("HardwareIds")
        fun getDeviceID(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun setHtmlText(textView: TextView, data: String?) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.text = Html.fromHtml(data, Html.FROM_HTML_MODE_COMPACT)
            }
        }

        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = connectivityManager.activeNetwork ?: return false
                val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
                return when {
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                val networkInfo = connectivityManager.activeNetworkInfo ?: return false
                return networkInfo.isConnected
            }
        }

        fun setStatusBarColor(activity: Activity, color: Int) {
            val window: Window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity, color)
        }

        fun getDeviceBranchAndModel(): String {
            return "$PLATFORM - ${Build.MANUFACTURER} ${Build.MODEL}"
        }

        fun getFormatDate(date:String): String? {
            return try {
                val parser = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
                val dateTime = parser.parseDateTime(date)
                val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd MMMM hh:mm a")
                dateTime.toString(formatter)
            }catch (e:Exception) {
                date
            }
        }

    }
}