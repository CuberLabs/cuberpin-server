package com.cuberlabs.cuperpinserver.domain.sms

interface Sms {
    fun sendMessage(destination: String, message: String)
}
