package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.producer

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCardCharge
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class GiftCardChargeEventProducer(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun publishEvent(giftCardCharge: GiftCardCharge) {
        applicationEventPublisher.publishEvent(GiftCardChargeCompleteEvent(giftCardCharge))
    }
}
