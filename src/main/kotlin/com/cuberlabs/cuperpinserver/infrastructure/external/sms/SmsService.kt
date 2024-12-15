package com.cuberlabs.cuperpinserver.infrastructure.sms

import com.cuberlabs.cuperpinserver.domain.sms.Sms
import org.springframework.stereotype.Component

@Component
class SmsService : Sms {
    override fun sendMessage(destination: String, message: String) {
        TODO("Not yet implemented")
    }
}