package com.cuberlabs.cuperpinserver.domain.banking

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank

interface Banking {
    fun sendMoney(amount: Int, bank: Bank, accountOwner: String): BankingStatus
}
