package com.cuberlabs.cuperpinserver.domain.user.controller.dto.response

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
) {}

