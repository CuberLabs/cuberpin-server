package com.cuberlabs.cuperpinserver.domain.user.controller.dto.request

data class LoginRequest(
    val phoneNumber: String,
    val password: String
)
