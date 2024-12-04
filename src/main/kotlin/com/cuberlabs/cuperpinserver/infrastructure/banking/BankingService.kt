package com.cuberlabs.cuperpinserver.infrastructure.banking

import com.cuberlabs.cuperpinserver.domain.banking.Banking
import com.cuberlabs.cuperpinserver.domain.banking.BankingStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank
import org.springframework.stereotype.Component

@Component
class BankingService(): Banking {
    override fun sendMoney(amount: Int, bank: Bank, accountOwner: String): BankingStatus {
        return BankingStatus(
            isSuccess = true,
            message = "Not yet implemented"
        )
    }

    override fun isFraudulentAccount(accountNumber: String): Boolean {
       return false
    }
}
