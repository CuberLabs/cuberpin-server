package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.GiftCardChargeRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.UpdateGiftCardStatusRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCard
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCardCharge
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardChargeRepository
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardRepository
import com.cuberlabs.cuperpinserver.domain.giftcardcharger.GiftCardCharger
import com.cuberlabs.cuperpinserver.infrastructure.exception.BusinessLogicException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID
import javax.transaction.Transactional

@Service
class GiftCardChargeService(
    private val giftCardRepository: GiftCardRepository,
    private val giftCardChargeRepository: GiftCardChargeRepository,
    private val giftCardCharger: GiftCardCharger
) {
    fun requestGiftCardCharge(giftCardChargeRequest: GiftCardChargeRequest) {
        val giftCards = giftCardChargeRequest.giftCards.map {
            giftCardRepository.save(
                GiftCard(
                    id = UUID.randomUUID(),
                    giftCode = it.giftCode,
                    amount = BigDecimal(0),
                    status = ChargeStatus.PENDING
                )
            )
        }

        val giftCardCharge = giftCardChargeRequest.run {
            GiftCardCharge(
                id = UUID.randomUUID(),
                bank = bank,
                accountOwner = bankAccountOwner,
                accountNumber = bankAccountNumber,
                chargeStatus = ChargeStatus.PENDING,
                giftCards = giftCards,
                totalAmount = BigDecimal(0)
            )
        }

        giftCardChargeRepository.save(giftCardCharge)

        giftCards.map {
            giftCardCharger.charge(it)
        }
    }

    @Transactional
    fun updateGiftCardStatus(giftCardUUID: UUID, req: UpdateGiftCardStatusRequest) {
        val giftCard = giftCardRepository.findByIdOrNull(giftCardUUID) ?: throw BusinessLogicException.GIFT_CARD_NOT_FOUND
        giftCard.updateAmount(req.amount)

        val giftCardCharge = giftCardChargeRepository.findByGiftCardsIn(giftCard) ?: throw BusinessLogicException.GIFT_CARD_CHARGE_NOT_FOUND
        giftCardCharge.updateTotalChargeStatus()
    }
}
