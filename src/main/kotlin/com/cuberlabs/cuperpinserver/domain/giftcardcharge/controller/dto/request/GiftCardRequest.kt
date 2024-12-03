package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.GiftCardType

data class GiftCardRequest(
    val giftCardType: GiftCardType,
    val giftCode: String
)
