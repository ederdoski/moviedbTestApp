package com.edominguez.moviedb.core.common.utils


import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.graphics.Insets
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.Html
import android.util.DisplayMetrics
import android.view.*
import android.view.animation.Animation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.network.PLATFORM
import java.io.File
import java.util.*


class Functions {

    companion object {

        fun openURL(context: Context, url: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }

        fun openEmailApp(context: Context) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_APP_EMAIL)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        fun openAppInPlayStore(context: Context, appPackageName: String) {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (error: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }

        @SuppressLint("HardwareIds")
        fun getDeviceID(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun copyText(activity: Activity, text: String) {
            val clipboardManager =
                activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", text)
            clipboardManager.setPrimaryClip(clipData)
        }

        fun pasteText(activity: Activity): String {
            val clipboardManager =
                activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val text: CharSequence? = clipboardManager.primaryClip?.getItemAt(0)?.text
            return text?.toString() ?: EMPTY_STRING
        }

        fun setHtmlText(textView: TextView, data: String?) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.text = Html.fromHtml(data, Html.FROM_HTML_MODE_COMPACT)
            }
        }

        fun isValidFilePath(filePath: String): Boolean {
            val file = File(filePath)
            return file.exists()
        }

        fun setStatusBarColor(activity: Activity, color: Int) {
            val window: Window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity, color)
        }

        fun openFile(file: File, typeFile: String, context: Context) {
            val url: Uri = FileProvider.getUriForFile(
                context,
                context.packageName + ".fileProvider",
                file
            )
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(url, typeFile)
            context.startActivity(
                Intent.createChooser(
                    intent,
                    context.getString(R.string.txt_open)
                )
            )
        }

        fun sharePlainText(subject: String, data: String, context: Context){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_SUBJECT,
                subject
            )
            intent.putExtra(Intent.EXTRA_TEXT, data)
            context.startActivity(Intent.createChooser(intent, data))
        }

        fun getDeviceBranchAndModel(): String {
            return "$PLATFORM - ${Build.MANUFACTURER} ${Build.MODEL}"
        }

        //---- SCREEN

        private fun getScreenWidth(activity: Activity): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics = activity.windowManager.currentWindowMetrics
                val insets: Insets = windowMetrics.windowInsets
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
                windowMetrics.bounds.width() - insets.left - insets.right
            } else {
                val displayMetrics = DisplayMetrics()
                activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
                displayMetrics.widthPixels
            }
        }

        fun animatedView(view: ConstraintLayout, animation: Animation){
            view.startAnimation(animation)
        }

    }
}