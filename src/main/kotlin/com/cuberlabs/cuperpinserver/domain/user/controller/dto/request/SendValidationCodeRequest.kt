package com.cuberlabs.cuperpinserver.domain.user.controller.dto.request

data class SendValidationCodeRequest(
    val name: String,
    val phoneNumber: String
)
