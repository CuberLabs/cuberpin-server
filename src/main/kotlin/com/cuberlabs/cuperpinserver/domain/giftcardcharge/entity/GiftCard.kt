package com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.*

@Entity
class GiftCard(
    id: UUID,
    giftCode: String,
    amount: BigDecimal,
    status: ChargeStatus
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID = id

    @Column(name = "gift_code", nullable = false)
    val giftCode: String = giftCode

    @Column(name = "amount", nullable = false)
    var amount: BigDecimal = amount

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: ChargeStatus = status

    fun updateAmount(amount: BigDecimal) {
        this.amount = amount
    }
}
