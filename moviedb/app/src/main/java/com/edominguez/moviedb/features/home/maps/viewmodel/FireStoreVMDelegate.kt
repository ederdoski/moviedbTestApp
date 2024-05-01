package com.edominguez.moviedb.features.home.maps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edominguez.moviedb.core.base.BaseVMDelegate
import com.edominguez.moviedb.features.home.maps.datasource.model.UserPositionResponseData

class FireStoreVMDelegate: BaseVMDelegate() {

    private val _onUsersLocationResponse = MutableLiveData<List<UserPositionResponseData>>()
    val onUsersLocationResponse: LiveData<List<UserPositionResponseData>> get() = _onUsersLocationResponse
    fun onUsersLocationResponsePostValue(data: List<UserPositionResponseData>) {
        _onUsersLocationResponse.postValue(data)
    }

    private val _onUsersLocationResponseFailed = MutableLiveData<Unit>()
    val onUsersLocationResponseFailed: LiveData<Unit> get() = _onUsersLocationResponseFailed
    fun onUsersLocationResponseFailedPostValue() {
        _onUsersLocationResponseFailed.postValue(Unit)
    }
}