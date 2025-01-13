package com.cuberlabs.cuperpinserver.infrastructure.external.sms

import com.cuberlabs.cuperpinserver.domain.sms.Sms
import com.cuberlabs.cuperpinserver.infrastructure.env.sms.SmsProperties
import com.cuberlabs.cuperpinserver.infrastructure.feign.sms.SmsClient
import com.cuberlabs.cuperpinserver.infrastructure.feign.sms.dto.request.SendSmsRequest
import org.springframework.stereotype.Component

@Component
class SmsService(
    private val smsProperties: SmsProperties,
    private val smsClient: SmsClient
) : Sms {
    override fun sendMessage(destination: String, message: String) {
        println("메세지 보냄")
        smsClient.sendSms(
            apiKey = smsProperties.apiKey,
            request = SendSmsRequest(
                tokenKey = smsProperties.tokenKey,
                msgBody = message,
                sendPhone = smsProperties.sendPhoneNumber,
                destPhone = destination,
                msgType = "sms"
            )
        )
    }
}