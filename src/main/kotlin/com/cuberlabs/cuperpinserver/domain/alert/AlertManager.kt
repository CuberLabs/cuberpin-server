package com.cuberlabs.cuperpinserver.domain.message

interface AlertManager {
    fun send(message: String)
}
