package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.GiftCardChargeRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.GiftCardChargeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/charge")
@RestController
class GiftCardChargeController(
    private val giftCardChargeService: GiftCardChargeService
) {
    @PostMapping
    fun giftCardCharge(
        @RequestBody
        giftCardChargeRequest: GiftCardChargeRequest
    ) {
        giftCardChargeService.requestGiftCardCharge(giftCardChargeRequest)
    }
}
