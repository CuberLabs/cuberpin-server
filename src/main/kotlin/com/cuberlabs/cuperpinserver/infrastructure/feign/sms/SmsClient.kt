package com.cuberlabs.cuperpinserver.infrastructure.feign.sms

import com.cuberlabs.cuperpinserver.infrastructure.feign.sms.dto.request.SendSmsRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "sms",
    url = "https://apis.ssodaa.com"
)
interface SmsClient {
    @PostMapping("/sms/send/sms")
    fun sendSms(
        @RequestHeader("x-api-key") apiKey: String,
        @RequestBody request: SendSmsRequest,
        @RequestHeader("x-forwarded-for") forwardedFor: String,
    )
}