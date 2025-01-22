package com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.listner

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.service.event.producer.GiftCardChargeCompleteEvent
import com.cuberlabs.cuperpinserver.domain.alert.AlertManager
import com.cuberlabs.cuperpinserver.domain.alert.Messages
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import com.cuberlabs.cuperpinserver.domain.sms.Sms
import com.cuberlabs.cuperpinserver.domain.sms.SmsMessages
import com.cuberlabs.cuperpinserver.infrastructure.external.discord.DiscordMessageService
import com.cuberlabs.cuperpinserver.infrastructure.websocket.service.ChargeSocketService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class GiftCardChargeEventListener(
    private val sms: Sms,
    private val chargeSocketService: ChargeSocketService,
    private val discordMessageService: DiscordMessageService
) {
    @Value("\${sms.admin-number}")
    var adminNumber: String = ""

    @EventListener
    fun handlerChargeCompleteEvent(event: GiftCardChargeCompleteEvent) {
        event.giftCardCharge.run {
            if (chargeStatus == ChargeStatus.COMPLETED || chargeStatus == ChargeStatus.PARTIALLY_DEPOSITED) {
                // 관리자에게 입금 요청
                discordMessageService.sendDepositRequestMessage(bank.fullName, accountNumber, depositAmount.toInt())
                event.giftCardCharge.run {
                    sms.sendMessage(
                        adminNumber,
                        SmsMessages.depositRequest(
                            bank = bank.toString(),
                            accountNumber = accountNumber,
                            amount = depositAmount.toInt().toString()
                        )
                    )
                }
            }
        }

        // 웹소켓 상태 변경
        chargeSocketService.changeChargeStatus(event.giftCardCharge.id!!, event.giftCardCharge.chargeStatus, event.giftCardCharge.depositAmount.toInt())
    }
}
