package com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity

import com.cuberlabs.cuperpinserver.domain.BaseUUIDEntity
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.GiftCardStatus
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class GiftCard(
    id: UUID?,
    giftCode: String,
    giftCardCharge: GiftCardCharge,
    giftCardStatus: GiftCardStatus
): BaseUUIDEntity(id) {
    @Column(name = "gift_code", nullable = false)
    var giftCode: String = giftCode
        protected set

    @Column(name = "gift_card_status", nullable = false)
    var giftCardStatus: GiftCardStatus = giftCardStatus
        protected set

    @ManyToOne
    @JoinColumn(name = "gift_card_charge_id")
    var giftCardCharge: GiftCardCharge = giftCardCharge
        protected set
}

