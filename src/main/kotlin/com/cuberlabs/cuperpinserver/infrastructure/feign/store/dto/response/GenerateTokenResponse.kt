package com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GenerateTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("expires_in")
    val expiresIn: Long,
    @JsonProperty("refresh_token")
    val tokenType: String,
)
