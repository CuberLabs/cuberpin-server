package com.cuberlabs.cuperpinserver.domain.message

import com.cuberlabs.cuperpinserver.domain.banking.BankingStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.GiftCardCharge
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus

object Messages {
    fun chargeStatus(giftCardCharge: GiftCardCharge, bankingStatus: BankingStatus): String {
        val chargeStatusMessage = when (giftCardCharge.chargeStatus) {
            ChargeStatus.COMPLETED -> "상품권 현금화가 완료되었습니다! 총 금액: ${giftCardCharge.totalAmount}원"
            ChargeStatus.PARTIALLY_DEPOSITED -> "상품권 일부가 충전되었습니다. 확인 부탁드립니다. 총 금액: ${giftCardCharge.totalAmount}원"
            else -> "상품권 충전에 실패하였습니다."
        }

        val bankingMessage = if (bankingStatus.isSuccess) {
            "입금이 성공적으로 처리되었습니다. (${giftCardCharge.bank.name} - ${giftCardCharge.accountOwner})"
        } else {
            "입금 처리 중 문제가 발생했습니다: ${bankingStatus.message}"
        }

        return """
        [상품권 충전 안내]
        ${chargeStatusMessage}
        ${bankingMessage}
        요청 시간: ${giftCardCharge.createdAt}
        최신 상태 업데이트 시간: ${giftCardCharge.updatedAt}
    """.trimIndent()
    }
}
