package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.GiftCardChargeRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.response.GiftCardChargeHistoryResponse
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.response.GiftCardChargeResponse
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.GiftCardChargeService
import com.cuberlabs.cuperpinserver.infrastructure.external.discord.DiscordMessageService
import org.springframework.web.bind.annotation.*

@RequestMapping("/charges")
@RestController
class GiftCardChargeController(
    private val giftCardChargeService: GiftCardChargeService,
    private val discordMessageService: DiscordMessageService
) {
    @PostMapping
    fun giftCardCharge(
        @RequestBody
        giftCardChargeRequest: GiftCardChargeRequest
    ): GiftCardChargeResponse {
        return giftCardChargeService.requestGiftCardCharge(giftCardChargeRequest)
    }

    @GetMapping
    fun giftCardChargeHistoryList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): List<GiftCardChargeHistoryResponse> {
        return giftCardChargeService.getChargeHistory(page, size)
    }
}
