package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.listner

import com.cuberlabs.cuperpinserver.domain.banking.Banking
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.producer.GiftCardChargeCompleteEvent
import com.cuberlabs.cuperpinserver.domain.alert.AlertManager
import com.cuberlabs.cuperpinserver.domain.alert.Messages
import com.cuberlabs.cuperpinserver.domain.sms.Sms
import com.cuberlabs.cuperpinserver.domain.sms.SmsMessages
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class GiftCardChargeEventListener(
    private val sms: Sms
) {
    @Value("\${sms.admin-number}")
    var adminNumber: String = ""
    @EventListener
    fun handlerChargeCompleteEvent(event: GiftCardChargeCompleteEvent) {
        // 관리자에게 입금 요청
        event.giftCardCharge.run {
            sms.sendMessage(
                adminNumber,
                SmsMessages.depositRequest(
                    bank = bank.toString(),
                    accountNumber = accountNumber,
                    amount = depositAmount.toString()
                )
            )
        }
    }
}
