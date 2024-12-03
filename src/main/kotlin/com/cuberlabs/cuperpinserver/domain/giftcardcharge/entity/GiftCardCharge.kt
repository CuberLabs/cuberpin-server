package com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity

import com.cuberlabs.cuperpinserver.domain.BaseUUIDEntity
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class GiftCardCharge(
    id: UUID,
    bank: Bank,
    accountOwner: String,
    accountNumber: String,
    chargeStatus: ChargeStatus,
    giftCards: List<GiftCard>
): BaseUUIDEntity(id) {
    var bank: Bank = bank
        protected set

    var accountOwner: String = accountOwner
        protected set

    var accountNumber: String = accountNumber
        protected set

    var chargeStatus: ChargeStatus = chargeStatus
        protected set

    @OneToMany(mappedBy = "giftCardCharge")
    var giftCards: List<GiftCard> = giftCards
        protected set
}
