package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.GiftCardType

data class GiftCardChargeRequest(
    val giftCards: List<GiftCardRequest>,
    val bank: Bank,
    val bankAccountNumber: String,
    val bankAccountOwner: String,
    val phoneNumber: String,
    val giftCardType: GiftCardType
)
