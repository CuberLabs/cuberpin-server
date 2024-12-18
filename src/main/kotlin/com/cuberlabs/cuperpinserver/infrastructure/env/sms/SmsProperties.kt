package com.cuberlabs.cuperpinserver.infrastructure.env.sms

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("sms")
data class SmsProperties(
    val apiKey: String,
    val tokenKey: String,
    val sendPhoneNumber: String
)
