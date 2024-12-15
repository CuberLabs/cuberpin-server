package com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request

data class GenerateTokenRequest(
    val clientId: String,
    val timestamp: String,
    val grantType: String,
    val clientSecret: String,
    val type: String,
)
