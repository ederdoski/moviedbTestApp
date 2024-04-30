package com.edominguez.moviedb.core.network

import com.edominguez.moviedb.core.network.model.NetworkData
import retrofit2.Response

class NetworkResponse<T>(responseNetwork: Response<T>) {
    var network: NetworkData = NetworkData(responseNetwork.code(), responseNetwork.message(), responseNetwork.errorBody())
    var data: T? = responseNetwork.body()
}