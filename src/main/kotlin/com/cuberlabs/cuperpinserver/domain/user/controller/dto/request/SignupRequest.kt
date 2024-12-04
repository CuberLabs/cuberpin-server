package com.cuberlabs.cuperpinserver.domain.user.controller.dto.request

data class SignupRequest(
    val phoneNumber: String,
    val name: String,
    val password: String
)
