package com.cuberlabs.cuperpinserver.infrastructure.feign.giftcard.dto.request

import java.util.UUID

data class ExternalGiftCardChargeRequest(
    val giftCardId: UUID,
    val giftCode: String
)
