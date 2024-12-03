package com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCard
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface GiftCardRepository : JpaRepository<GiftCard, UUID> {

}
