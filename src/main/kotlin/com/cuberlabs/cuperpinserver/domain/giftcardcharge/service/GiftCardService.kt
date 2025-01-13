package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.UpdateGiftCardStatusRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardChargeRepository
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardRepository
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.listner.GiftCardChargeEventListener
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.producer.GiftCardChargeEventProducer
import com.cuberlabs.cuperpinserver.infrastructure.exception.BusinessLogicException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class GiftCardService(
    private val giftCardRepository: GiftCardRepository,
    private val giftCardChargeRepository: GiftCardChargeRepository,
    private val giftCardChargeEventProducer: GiftCardChargeEventProducer
) {
    @Transactional
    fun updateGiftCardStatus(giftCardUUID: UUID, req: UpdateGiftCardStatusRequest) {
        val giftCard = giftCardRepository.findByIdOrNull(giftCardUUID) ?: throw BusinessLogicException.GIFT_CARD_NOT_FOUND
        giftCard.updateAmount(req.amount)
        giftCardRepository.save(giftCard)

        val giftCardCharge = giftCardChargeRepository.findByGiftCards(giftCard) ?: throw BusinessLogicException.GIFT_CARD_CHARGE_NOT_FOUND
        giftCardCharge.updateTotalChargeStatus()

        if(giftCardCharge.chargeStatus == ChargeStatus.COMPLETED || giftCardCharge.chargeStatus == ChargeStatus.PARTIALLY_DEPOSITED) {
            giftCardCharge.calculateDepositAmount()
            giftCardChargeEventProducer.publishEvent(giftCardCharge)
        }
    }
}
