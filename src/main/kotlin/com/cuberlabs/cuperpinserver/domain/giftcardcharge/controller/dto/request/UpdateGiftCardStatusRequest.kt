package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request

import java.math.BigDecimal

data class UpdateGiftCardStatusRequest(
    val amount: BigDecimal
)
