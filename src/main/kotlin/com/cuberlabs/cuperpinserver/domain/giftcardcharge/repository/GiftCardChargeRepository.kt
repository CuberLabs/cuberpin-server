package com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCardCharge
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface GiftCardChargeRepository : JpaRepository<GiftCardCharge, UUID> {
    fun findByGiftCardsIn()
}
