package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.listner

import com.cuberlabs.cuperpinserver.domain.banking.Banking
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.producer.GiftCardChargeCompleteEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class GiftCardChargeEventListener(
    private val banking: Banking
) {
    @EventListener
    fun handlerChargeCompleteEvent(event: GiftCardChargeCompleteEvent) {
        // depositAmount를 실제 계좌로 송금
        val bankingStatus = event.giftCardCharge.run {
            banking.sendMoney(
                amount = depositAmount.toInt(),
                bank = bank,
                accountOwner = accountOwner
            )
        }

        if(!bankingStatus.isSuccess) {
            // 실패 로직
        }

        // 송금 상태를 Discord로 알림
    }
}
