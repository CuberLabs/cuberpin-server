package com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.UpdateGiftCardStatusRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.GiftCardService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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
