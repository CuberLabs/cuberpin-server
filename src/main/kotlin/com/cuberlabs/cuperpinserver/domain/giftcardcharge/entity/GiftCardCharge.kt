package com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity

import com.cuberlabs.cuperpinserver.domain.BaseUUIDEntity
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import java.math.BigDecimal
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
    totalAmount: BigDecimal
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

    @OneToMany(mappedBy = "giftCardCharge", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var giftCards: List<GiftCard> = giftCards
        protected set

    @Column(name = "total_amount", nullable = false)
    var totalAmount: BigDecimal = totalAmount
        protected set

    // 상태 업데이트 메서드
    fun updateChargeStatus(newStatus: ChargeStatus) {
        chargeStatus = newStatus
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
    }
}
