package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.producer

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCardCharge

data class GiftCardChargeCompleteEvent(
    val giftCardCharge: GiftCardCharge
)
