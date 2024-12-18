package com.cuberlabs.cuperpinserver.infrastructure.feign.sms.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SendSmsRequest(
    val tokenKey: String,
    val msgType: String,
    val destPhone: String,
    val sendPhone: String,
    val msgBody: String
)
