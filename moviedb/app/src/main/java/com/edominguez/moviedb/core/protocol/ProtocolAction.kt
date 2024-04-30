package com.edominguez.moviedb.core.protocol

import com.edominguez.moviedb.core.common.utils.EMPTY_STRING

sealed class ProtocolAction {

    /** VERIFY PHONE NUMBER PROTOCOlS*/

    class OnLoading(val loading: Boolean) : ProtocolAction()
    object OnPlatformError : ProtocolAction()
    object OnNetworkError : ProtocolAction()


    /** HOME PROTOCOLS */
    class OnSelectedHomeItem(val option: Int) : ProtocolAction()
    class OnSelectedMapsItem(val option: Int) : ProtocolAction()
}