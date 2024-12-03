package com.cuberlabs.cuperpinserver.domain.giftcardcharger.impl

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCard
import com.cuberlabs.cuperpinserver.domain.giftcardcharger.GiftCardCharger
import com.cuberlabs.cuperpinserver.infrastructure.feign.giftcard.GiftCardClient
import com.cuberlabs.cuperpinserver.infrastructure.feign.giftcard.dto.request.ExternalGiftCardChargeRequest
import org.springframework.stereotype.Component

@Component
class GiftCardChargerImpl(
    private val giftCardClient: GiftCardClient
) : GiftCardCharger {
    override fun charge(giftCard: GiftCard) {
        giftCardClient.giftCardCharge(
            ExternalGiftCardChargeRequest(
                giftCardId = giftCard.id!!,
                giftCode = giftCard.giftCode
            )
        )
    }
}
