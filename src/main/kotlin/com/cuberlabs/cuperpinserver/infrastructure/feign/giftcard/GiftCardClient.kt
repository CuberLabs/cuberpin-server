package com.cuberlabs.cuperpinserver.infrastructure.feign.giftcard

import com.cuberlabs.cuperpinserver.infrastructure.feign.giftcard.dto.request.ExternalGiftCardChargeRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "GiftCard",
    url = "\${url.gift-card}"
)
interface GiftCardClient {
    @PostMapping("/charge")
    fun giftCardCharge(
        @RequestBody
        externalGiftCardChargeRequest: ExternalGiftCardChargeRequest
    )
}
