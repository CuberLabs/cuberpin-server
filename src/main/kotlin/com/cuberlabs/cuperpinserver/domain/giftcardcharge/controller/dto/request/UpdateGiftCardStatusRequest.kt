package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import java.math.BigDecimal

data class UpdateGiftCardStatusRequest(
    val amount: BigDecimal,
    val chargeStatus: ChargeStatus
)
