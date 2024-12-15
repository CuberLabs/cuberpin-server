package com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class GenerateTokenRequest(
    val clientId: String,
    val timestamp: Long,
    val grantType: String,
    val clientSecret: String,
    val type: String,
)
