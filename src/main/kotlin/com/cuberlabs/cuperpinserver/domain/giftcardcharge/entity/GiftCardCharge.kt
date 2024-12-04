package com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity

import com.cuberlabs.cuperpinserver.domain.BaseTimeEntity
import com.cuberlabs.cuperpinserver.domain.BaseUUIDEntity
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.GiftCardType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
class GiftCardCharge(
    id: UUID,
    bank: Bank,
    accountOwner: String,
    accountNumber: String,
    chargeStatus: ChargeStatus,
    giftCards: List<GiftCard>,
    totalAmount: BigDecimal,
    depositAmount: BigDecimal,
    giftCardType: GiftCardType
) : BaseUUIDEntity(id) {

    @Enumerated(EnumType.STRING)
    @Column(name = "bank", nullable = false)
    var bank: Bank = bank
        protected set

    @Column(name = "account_owner", nullable = false)
    var accountOwner: String = accountOwner
        protected set

    @Column(name = "account_number", nullable = false)
    var accountNumber: String = accountNumber
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "charge_status", nullable = false)
    var chargeStatus: ChargeStatus = chargeStatus
        protected set

    @OneToMany
    @JoinColumn(name = "gift_card_id")
    var giftCards: List<GiftCard> = giftCards
        protected set

    @Column(name = "total_amount", nullable = false)
    var totalAmount: BigDecimal = totalAmount
        protected set

    @Column(name = "gift_card_type", nullable = false)
    var giftCardType: GiftCardType = giftCardType
        protected set

    @Column(name = "deposit_amount", nullable = false)
    var depositAmount: BigDecimal = depositAmount
        protected set

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()

    companion object {
        val TRANSFER_FEE = BigDecimal(500)
    }

    fun calculateDepositAmount() {
        when(giftCardType) {
            GiftCardType.CULTURE -> depositAmount = giftCards.sumOf { it.amount } * BigDecimal(GiftCardFee.CULTURE_LAND) - TRANSFER_FEE
        }
    }

    // 상품권 금액 합산 후 전체 금액 업데이트
    fun updateTotalAmount() {
        totalAmount = giftCards.sumOf { it.amount }
    }

    // 전체 상품권 요청 상태에 따라 chargeStatus를 업데이트
    fun updateTotalChargeStatus() {
        when {
            giftCards.any { it.status == ChargeStatus.PENDING } -> chargeStatus = ChargeStatus.PENDING
            giftCards.all { it.status == ChargeStatus.COMPLETED } -> chargeStatus = ChargeStatus.COMPLETED
            giftCards.all { it.status == ChargeStatus.FAILED } -> chargeStatus = ChargeStatus.FAILED
            giftCards.any { it.status == ChargeStatus.FAILED }
                    and giftCards.any { it.status == ChargeStatus.COMPLETED } -> chargeStatus = ChargeStatus.PARTIALLY_DEPOSITED
        }
        updateTotalAmount()
    }
}
