package com.cuberlabs.cuperpinserver.infrastructure.websocket.service

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import com.cuberlabs.cuperpinserver.infrastructure.websocket.ChargeSocketHandler
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChargeSocketService(
    private val chargeSocketHandler: ChargeSocketHandler
) {
    fun startCharge(chargeId: UUID) {
        chargeSocketHandler.notifyRechargeState(chargeId.toString(), "CHARGE ACCEPT $chargeId")
    }

    fun changeChargeStatus(chargeId: UUID, chargeStatus: ChargeStatus, amount: Int) {
        chargeSocketHandler.notifyRechargeState(chargeId.toString(), "CHARGE STATUS $chargeId $chargeStatus $amount")
    }
}