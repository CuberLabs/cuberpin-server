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
): BaseUUIDEntity(id) {
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

    @Column(name = "charge_status", nullable = false)
    var chargeStatus: ChargeStatus = chargeStatus
        protected set

    @OneToMany(mappedBy = "giftCardCharge")
    var giftCards: List<GiftCard> = giftCards
        protected set

    @Column(name = "total_amount", nullable = false)
    var totalAmount: BigDecimal = totalAmount
        protected set
}
