package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.response

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.GiftCardType
import java.time.LocalDateTime

data class GiftCardChargeHistoryResponse(
    val giftCardType: GiftCardType,
    val amount: Int,
    val chargeStatus: ChargeStatus,
    val chargedAt: LocalDateTime
)
