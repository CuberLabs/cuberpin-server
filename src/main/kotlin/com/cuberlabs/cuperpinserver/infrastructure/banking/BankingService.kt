package com.cuberlabs.cuperpinserver.infrastructure.banking

import com.cuberlabs.cuperpinserver.domain.banking.Banking
import com.cuberlabs.cuperpinserver.domain.banking.BankingStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank
import com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking.NhBankingClient
import com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking.dto.request.TransferOtherBankRequest
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class BankingService(
    private val bankingClient: NhBankingClient
): Banking {
    override fun sendMoney(amount: Int, bank: Bank, accountOwner: String, accountNumber: String): BankingStatus {
        val response = bankingClient.transferOtherBank(
            TransferOtherBankRequest(
                bncd = bank.code,
                acno = accountNumber,
                tram = amount.toString(),
                dractOtlt = "쿠버핀",
                mractOtlt = "쿠버핀"
            ),
            istuno = Random.nextInt(100000, 1000000).toString()
        )

        print(response)

        return BankingStatus(
            isSuccess = response.responseCode == "00000",
            statusCode = response.responseCode,
            message = response.responseMessage
        )
    }

    override fun isFraudulentAccount(accountNumber: String): Boolean {
        // Not yet implemented
        return false
    }
}
