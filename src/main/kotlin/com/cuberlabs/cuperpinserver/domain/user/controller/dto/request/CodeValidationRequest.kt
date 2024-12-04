package com.cuberlabs.cuperpinserver.domain.user.controller.dto.request

data class CodeValidationRequest(
    val phoneNumber: String,
    val validationCode: Int
)
