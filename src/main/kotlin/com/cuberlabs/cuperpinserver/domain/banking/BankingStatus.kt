package com.cuberlabs.cuperpinserver.domain.banking

data class BankingStatus(
    val isSuccess: Boolean,
    val statusCode: String,
    val message: String
)
