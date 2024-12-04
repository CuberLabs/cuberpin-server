package com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCard
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCardCharge
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface GiftCardChargeRepository : JpaRepository<GiftCardCharge, UUID> {
    fun findByGiftCards(giftCard: GiftCard): GiftCardCharge?
    @Query("SELECT g FROM GiftCardCharge g ORDER BY g.updatedAt DESC")
    fun findLatest(pageable: Pageable): Page<GiftCardCharge>
}
