package com.cuberlabs.cuperpinserver.domain.alert

interface AlertManager {
    fun send(message: String)
}
