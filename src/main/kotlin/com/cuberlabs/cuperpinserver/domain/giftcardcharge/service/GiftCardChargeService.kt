package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service

import com.cuberlabs.cuperpinserver.domain.banking.Banking
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.request.GiftCardChargeRequest
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.controller.dto.response.GiftCardChargeHistory
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCard
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCardCharge
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardChargeRepository
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardRepository
import com.cuberlabs.cuperpinserver.domain.giftcardcharger.GiftCardCharger
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class GiftCardChargeService(
    private val giftCardRepository: GiftCardRepository,
    private val giftCardChargeRepository: GiftCardChargeRepository,
    private val giftCardCharger: GiftCardCharger,
    private val banking: Banking
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
                totalAmount = BigDecimal(0),
                depositAmount = BigDecimal(0),
                giftCardType = giftCardType,
                phoneNumber = phoneNumber
            )
        }

        giftCardChargeRepository.save(giftCardCharge)

        if(banking.isFraudulentAccount(giftCardCharge.accountNumber)) {
            giftCardCharge.giftCards.all {
                it.status == ChargeStatus.FAILED
            }
            giftCardCharge.updateTotalChargeStatus()
            return
        }

        giftCards.map {
            giftCardCharger.charge(it)
        }
    }

    fun getChargeHistory(page: Int, size: Int): List<GiftCardChargeHistory> {
        val pageable: Pageable = PageRequest.of(page, size)
        return giftCardChargeRepository.findLatest(pageable).content.map {
            GiftCardChargeHistory(
                giftCardType = it.giftCardType,
                amount = it.totalAmount.toInt(),
                chargeStatus = it.chargeStatus,
                chargedAt = it.updatedAt
            )
        }
    }
}
