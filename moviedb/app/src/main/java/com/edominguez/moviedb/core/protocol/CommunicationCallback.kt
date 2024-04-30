package com.edominguez.moviedb.core.protocol

interface CommunicationCallback {
    fun onFragmentEvent(action: ProtocolAction)
}