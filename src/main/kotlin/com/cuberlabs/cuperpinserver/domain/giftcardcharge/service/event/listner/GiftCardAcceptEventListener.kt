package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.listner

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.producer.GiftCardChargeAcceptEvent
import com.cuberlabs.cuperpinserver.infrastructure.websocket.service.ChargeSocketService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class GiftCardAcceptEventListener(
    private val chargeSocketService: ChargeSocketService
) {
    @EventListener
    fun handlerChargeAcceptEvent(giftCardChargeAcceptEvent: GiftCardChargeAcceptEvent) {
        chargeSocketService.startCharge(giftCardChargeAcceptEvent.giftCardCharge.id!!)
    }
}