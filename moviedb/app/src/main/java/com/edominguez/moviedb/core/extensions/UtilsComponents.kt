package com.edominguez.moviedb.core.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Paint
import android.os.Build
import android.text.Editable
import android.text.SpannableString
import android.text.method.DigitsKeyListener
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.edominguez.moviedb.core.common.utils.EMPTY_STRING
import com.edominguez.moviedb.core.common.utils.SPACE_STRING
import com.edominguez.moviedb.core.common.utils.ZERO
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.disableByAlpha(alpha: Float = 0.5f) {
    this.alpha = alpha
    this.isEnabled = false
}

fun Guideline.changePercent(newPercent: Float) {
    val params = this.layoutParams as ConstraintLayout.LayoutParams
    params.guidePercent = newPercent
    this.layoutParams = params
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.validateUpperChar(): Boolean = Regex("[A-Z]").containsMatchIn(this)
fun String.validateContainsNumber(): Boolean = Regex("[0-9]").containsMatchIn(this)
fun String.validateContainsChar(): Boolean = Regex("[A-z]").containsMatchIn(this)
fun String.validateContainsSpecialChar(): Boolean =
    Regex("[-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_]").containsMatchIn(this)

fun String.validateAlphanumeric42Length(): Boolean =
    Regex("^[0-9a-zA-Z]{42}\$").containsMatchIn(this)

fun String.validateAlphanumeric32to42Length(): Boolean =
    Regex("^[0-9a-zA-Z]{32,42}\$").containsMatchIn(this)

fun String.validateStringField(): Boolean = Regex("^[a-zA-Z ]*\$").containsMatchIn(this)

fun Int.dpToPx(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()
}

fun Int.formatNumberInDD(): String {
    return if(this < 10) {
        "0$this"
    }else{
        this.toString()
    }
}

fun Int.getHourFormat(): String = if (this < 10) "0$this" else "$this"


fun String.upperCaseFirstLetter(): String {
    return if(this.isNotBlank()) {
        this.substring(0, 1).uppercase() + this.substring(1).lowercase()
    }else{
        EMPTY_STRING
    }
}

fun String.initials(): String {
    return this.substring(0, 1).uppercase()
}

fun String.creditCardFormatted(): String {
    val result = StringBuilder()
    for (i in this.indices) {
        if (i % 4 == 0 && i != 0) {
            result.append(" ")
        }
        result.append(this[i])
    }
    return result.toString()
}

fun ImageView.setImageSrcFromUrl(url: String?, placeHolder: Int, context: Context) {
    Glide.with(context).load(url).placeholder(placeHolder)
        .into(this)
}

fun ImageView.setImageSrcFromDrawable(drawable: Int, context: Context) {
    Glide.with(context).load(drawable).into(this)
}

fun ImageView.setImageSrcFromDrawable(drawable: Int) {
    this.setImageResource(drawable)
}

fun TextView.removeDrawable() {
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
}

fun TextView.removePaddings() {
    this.setPadding(ZERO, ZERO, ZERO, ZERO)
}

fun String.removeQuotes(): String {
    return this.replace(",", "")
}

fun String.removeWhiteSpaces(): String {
    return this.replace(SPACE_STRING, EMPTY_STRING)
}

fun String.formatDots(): String {
    // Reemplaza todas las ocurrencias de ".." por un solo "."
    val cleanedValue = this.replace("\\.\\.".toRegex(), ".")

    // Separa la cadena por puntos y luego filtra los elementos vacíos
    val parts = cleanedValue.split('.').filter { it.isNotBlank() }

    // Une las partes con un solo punto como separador
    return parts.joinToString(".")
}

fun Float.formatValueToIntIfDoesntHaveDecimals(): String {
    return if (this % 1.0 != 0.0) {
        this.toString()
    } else {
        this.toInt().toString()
    }
}

fun Float.formatValueWithTwoDecimals(): String {
    val format = NumberFormat.getInstance(Locale.US).format(this)
    return if (this % 1 == 0.0F) {
        "$format.00"
    } else {
        format
    }
}

fun BigDecimal.formatValueTwoSixDecimals(): BigDecimal {
    val bigDecimal = this
    return bigDecimal.setScale(2, RoundingMode.HALF_UP)
}

fun Float.formatValue(): String {
    val value = this
    val formatter = DecimalFormat("#,###,##0.00", DecimalFormatSymbols(Locale.US))
    return formatter.format(this)
}

fun Float.formatValueWithOutDecimals(): String {
    val formatter = DecimalFormat("#,###,###", DecimalFormatSymbols(Locale.US))
    return formatter.format(this)
}

fun Float.formatValueWithOneDecimal(): String {
    val formatter = DecimalFormat("#,###,##0.0", DecimalFormatSymbols(Locale.US))
    return formatter.format(this)
}

fun Float.genericformatAmountValue(): String {
    val formatter = DecimalFormat("#,###,###.##", DecimalFormatSymbols(Locale.US))
    return formatter.format(this)
}

fun BigDecimal.formatValue(): String {
    val formatter = DecimalFormat("#,###,##0.00", DecimalFormatSymbols(Locale.US))
    return formatter.format(this.setScale(2,RoundingMode.CEILING))
}


fun BigDecimal.formatCryptoCurrencyValue(): String {
    val formatter = DecimalFormat("#,###,##0.00####", DecimalFormatSymbols(Locale.US))
    return formatter.format(this.setScale(6,RoundingMode.CEILING))
}

fun String.getTwoDecimals(): String {
    return this.substring(0, 2)
}

fun EditText.setDigitKeyListener() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.keyListener = DigitsKeyListener.getInstance(Locale.US, true, true)
    } else {
        this.keyListener = DigitsKeyListener.getInstance(true, true)
    }
}

// ------- Methods

fun String.hideEmailInfo(): String {
    val userSplit = this.substring(0, this.indexOf("@"))
    val companySplit = this.substring(this.indexOf("@"), this.length)
    val lastUserNameChars: String
    val hideEmail: String
    var tempChars = ""

    val lengthOfStars: Int = if (userSplit.length > 4) {
        userSplit.substring(0, userSplit.length - 3).length
    } else {
        userSplit.substring(0, userSplit.length - 1).length
    }

    lastUserNameChars = userSplit.substring(lengthOfStars, userSplit.length)
    for (i in 1..lengthOfStars) {
        tempChars += "*"
    }
    hideEmail = tempChars + lastUserNameChars + companySplit
    return hideEmail
}

fun String.hideFullEmailInfo(): String {
    val userSplit = this.substring(0, this.indexOf("@"))
    val companySplit = this.substring(this.indexOf("@"), this.length)
    val hideEmail: String
    var tempChars = ""

    val lengthOfStars: Int = userSplit.length
    for (i in 1..lengthOfStars) {
        tempChars += "*"
    }
    hideEmail = tempChars + companySplit
    return hideEmail
}

fun String.hideBankAccountNumber(): String {
    val stringLength = this.length
    var tempString = ""
    for (i in 0..(stringLength - 5)) {
        tempString += "\u25CF"
    }
    tempString += this.substring(stringLength - 4, stringLength)
    return tempString
}

fun String.hideBalance(): String {
    return "••••"
}

fun View.showKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun TextView.setColor(context: Context, color: Int) {
    this.setTextColor(ContextCompat.getColor(context, color))
}

fun TextView.underline(text: String) {
    val auxText = SpannableString(text)
    auxText.setSpan(UnderlineSpan(), 0, auxText.length, 0)
    this.text = auxText
}

fun LinearLayout.getAllViews(): List<View> {
    val result = ArrayList<LinearLayout>()
    val childs = this.childCount - 1
    for (i in 0..childs)
        result.add(this.getChildAt(i) as LinearLayout)
    return result
}

fun String.getCleanString(): String {
    val values = this.split("_")
    var finalString = EMPTY_STRING
    values.map {
        finalString += it.replaceFirstChar { firstChart -> firstChart.uppercase() } + SPACE_STRING
    }
    return finalString
}

fun ScrollView.scrollToBottom() {
    this.post {
        this.fullScroll(ScrollView.FOCUS_DOWN)
    }
}

fun BigDecimal.formatCurrencyValue(): String {
    val amount = this
    amount.setScale(2,RoundingMode.HALF_EVEN)
    val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
    val symbols: DecimalFormatSymbols = formatter.decimalFormatSymbols
    symbols.currencySymbol = EMPTY_STRING
    formatter.decimalFormatSymbols = symbols
    return formatter.format(amount)
}

fun Fragment.onNavigationBackPressed(listener: () -> Unit) {
    val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            listener()
        }
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}

fun Button.enabled(context: Context, backgroundColor: Int, textColor: Int) {
    this.backgroundTintList =
        ContextCompat.getColorStateList(context, backgroundColor)
    this.setTextColor(ContextCompat.getColor(context, textColor))
    this.isEnabled = true
}

fun Button.disable(context: Context, backgroundColor: Int, textColor: Int) {
    this.backgroundTintList =
        ContextCompat.getColorStateList(context, backgroundColor)
    this.setTextColor(ContextCompat.getColor(context, textColor))
    this.isEnabled = false
}

fun BigDecimal.roundToSixDigits(): BigDecimal {
    return this.setScale(
        6,
        RoundingMode.HALF_UP
    )
}
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun String.capitalizeWord(): String = this.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

fun TextView.strikeText(show: Boolean) {
    paintFlags =
        if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

