package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.UpdateGiftCardStatusRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.GiftCardService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/gift-card")
@RestController
class GiftCardController(
    private val giftCardService: GiftCardService
) {
    @PutMapping("/status/{giftCardId}")
    fun updateGiftCardStatus(
        @RequestBody
        updateGiftCardStatusRequest: UpdateGiftCardStatusRequest,
        @PathVariable
        giftCardId: UUID
    ) {
        giftCardService.updateGiftCardStatus(
            giftCardUUID = giftCardId,
            req = updateGiftCardStatusRequest
        )
    }
}
