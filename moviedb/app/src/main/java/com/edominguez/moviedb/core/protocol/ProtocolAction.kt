package com.edominguez.moviedb.core.protocol

import com.edominguez.moviedb.core.common.utils.EMPTY_STRING

sealed class ProtocolAction {

    /** VERIFY PHONE NUMBER PROTOCOlS*/

    class OnLoading(val loading: Boolean) : ProtocolAction()
    object OnPlatformError : ProtocolAction()
    object OnNetworkError : ProtocolAction()
    class OnGoToCardFeature(val message:String = EMPTY_STRING, val description:String = EMPTY_STRING, val haveTransactionsInWallet:Boolean = true, val isRestrictedCountry:Boolean = false) : ProtocolAction()

}