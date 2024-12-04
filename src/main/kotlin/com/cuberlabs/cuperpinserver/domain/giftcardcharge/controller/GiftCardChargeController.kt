package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.GiftCardChargeRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.response.GiftCardChargeHistory
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.GiftCardChargeService
import org.springframework.web.bind.annotation.*

@RequestMapping("/charges")
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

    @GetMapping
    fun giftCardChargeHistoryList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): List<GiftCardChargeHistory> {
        return giftCardChargeService.getChargeHistory(page, size)
    }
}
