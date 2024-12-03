package com.cuberlabs.cuperpinserver.domain.giftcardcharger

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCard

interface GiftCardCharger {
    fun charge(giftCard: GiftCard)
}
