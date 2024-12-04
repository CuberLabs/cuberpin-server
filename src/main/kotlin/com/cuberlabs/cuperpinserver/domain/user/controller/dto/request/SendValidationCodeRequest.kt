package com.cuberlabs.cuperpinserver.domain.user.controller.dto.request

data class UserSendCodeRequest(
    val name: String,
    val phoneNumber: String
)
