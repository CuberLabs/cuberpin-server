package com.cuberlabs.cuperpinserver.infrastructure.alert

import com.cuberlabs.cuperpinserver.domain.alert.AlertManager
import org.springframework.stereotype.Component

@Component
class AlertService : AlertManager {
    override fun send(message: String) {
        TODO("Not yet implemented")
    }
}
