package com.edominguez.moviedb.core.network

import android.content.Context
import com.edominguez.moviedb.BuildConfig
import com.edominguez.moviedb.core.common.utils.Functions
import com.edominguez.moviedb.core.preferences.Preferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


//---- API
const val API_AUTH = "private"
const val API_WITHOUT_AUTH = "public"

//---- Headers
const val HEADER_AUTH        = "Authorization"
const val BEARER             = "Bearer "
const val HEADER_PLATFORM    = "Platform"
const val HEADER_DEVICE_ID   = "Device_Id"
const val PLATFORM           = "Android"
const val HEADER_APP_VERSION = "App-Version"
const val HEADER_APP_VERSION_CODE = "App-Version-Code"
const val HEADER_DEVICE_DATA = "Device-Data"

//---- AMBIENT
const val DEBUG = "DEBUG"
const val PROD  = "PROD"

val networkModule: Module = module {

    single { providerHttpLoggingInterceptor() }
    single { getRetrofitBase() }

    //----WithoutAuth Api
    single (named(API_WITHOUT_AUTH)) {
        providerPublicRetrofit(
            retrofitBase = get(),
            context = get(),
            httpLoggingInterceptor = get()
        )
    }

    //----Auth Api
    single (named(API_AUTH)) {
        providerPrivateRetrofit(
            retrofitBase = get(),
            preferences = get(),
            context = get(),
            httpLoggingInterceptor = get()
        )
    }
}

private fun getRetrofitBase(): Retrofit.Builder {
    return Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
}

private fun getBasicHeaders(context: Context, chain: Interceptor.Chain): Request.Builder {
    val builder = chain.request().newBuilder()
    builder.addHeader(HEADER_APP_VERSION, BuildConfig.VERSION_NAME)
    builder.addHeader(HEADER_APP_VERSION_CODE, BuildConfig.VERSION_CODE.toString())
    builder.addHeader(HEADER_PLATFORM, PLATFORM)
    builder.addHeader(HEADER_DEVICE_ID, Functions.getDeviceID(context))
    builder.addHeader(HEADER_DEVICE_DATA, Functions.getDeviceBranchAndModel())
    return builder
}

fun providerPublicRetrofit(retrofitBase: Retrofit.Builder, context: Context, httpLoggingInterceptor:HttpLoggingInterceptor): Retrofit {
    return retrofitBase
        .client(providerPublicHttpClient(httpLoggingInterceptor, context))
        .build()
}

fun providerPrivateRetrofit(retrofitBase: Retrofit.Builder, context: Context, preferences: Preferences, httpLoggingInterceptor:HttpLoggingInterceptor): Retrofit {
    return retrofitBase
        .client(providerPrivateHttpClient(httpLoggingInterceptor,  context, preferences))
        .build()
}

private fun providerPrivateHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, context: Context, preferences: Preferences): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
        val builder = getBasicHeaders(context, chain)
        builder.addHeader(HEADER_AUTH, BEARER + preferences.jwt)
        val response = chain.proceed(builder.build())
        response
    }
    setTimeOuts(httpClient)
    httpClient.addInterceptor(httpLoggingInterceptor)
    return httpClient.build()
}

private fun providerPublicHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, context: Context): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
        val builder = getBasicHeaders(context, chain)
        chain.proceed(builder.build())
    }
    setTimeOuts(httpClient)
    httpClient.addInterceptor(httpLoggingInterceptor)
    return httpClient.build()
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.AMBIENT == DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    return logging
}

private fun setTimeOuts(httpClient: OkHttpClient.Builder) {
    httpClient.connectTimeout(30, TimeUnit.SECONDS)
    httpClient.readTimeout(30, TimeUnit.SECONDS)
    httpClient.writeTimeout(30, TimeUnit.SECONDS)
}


