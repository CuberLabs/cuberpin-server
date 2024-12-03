package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.UpdateGiftCardStatusRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardChargeRepository
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardRepository
import com.cuberlabs.cuperpinserver.infrastructure.exception.BusinessLogicException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class GiftCardService(
    private val giftCardRepository: GiftCardRepository,
    private val giftCardChargeRepository: GiftCardChargeRepository
) {
    @Transactional
    fun updateGiftCardStatus(giftCardUUID: UUID, req: UpdateGiftCardStatusRequest) {
        val giftCard = giftCardRepository.findByIdOrNull(giftCardUUID) ?: throw BusinessLogicException.GIFT_CARD_NOT_FOUND
        giftCard.updateAmount(req.amount)

        val giftCardCharge = giftCardChargeRepository.findByGiftCards(giftCard) ?: throw BusinessLogicException.GIFT_CARD_CHARGE_NOT_FOUND
        giftCardCharge.updateTotalChargeStatus()
    }
}
