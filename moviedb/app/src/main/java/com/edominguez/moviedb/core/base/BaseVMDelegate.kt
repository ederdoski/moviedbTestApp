package com.edominguez.moviedb.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class BaseVMDelegate {
    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading: LiveData<Boolean> get() = _onLoading
    fun loadingPostValue(loading: Boolean) {
        _onLoading.postValue(loading)
    }

    private val _unknownError = MutableLiveData<Throwable>()
    val showUnknownError: LiveData<Throwable> get() = _unknownError
    fun showUnknownErrorPostValue(error: Throwable) {
        loadingPostValue(false)
        _unknownError.postValue(error)
    }

    private val _onNetworkError = MutableLiveData<Unit>()
    val onNetworkError: LiveData<Unit> get() = _onNetworkError
    private fun onNetworkErrorPostValue() {
        loadingPostValue(false)
        _onNetworkError.postValue(Unit)
    }

    // TODO se usa para hacer catch de timeout pero esta detectando un error antes en el manejo de la conexion de red
    fun exceptionHandler(): CoroutineContext {
        return Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            loadingPostValue(false)
            onNetworkErrorPostValue()
        }
    }
}
