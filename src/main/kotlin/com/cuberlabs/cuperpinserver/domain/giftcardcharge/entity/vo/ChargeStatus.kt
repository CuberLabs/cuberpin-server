package com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo

enum class ChargeStatus {
    PENDING,
    COMPLETED,
    FAILED,
    PARTIALLY_DEPOSITED, // 일부 입금
    WAITING_FOR_DEPOSIT, // 입금 대기중
    WAITING_FOR_PARTIALLY_DEPOSITED // 일부입금 대기중
}
