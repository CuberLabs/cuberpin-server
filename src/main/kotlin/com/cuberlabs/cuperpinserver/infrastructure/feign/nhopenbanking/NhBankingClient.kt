package com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking

import com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking.config.NhBankingCommonRequest
import com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking.dto.request.TransferOtherBankRequest
import com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking.dto.response.NhBankingCommonResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "nh-banking", url = "\${url.nh-banking}", configuration = [NhBankingCommonRequest::class])
interface NhBankingClient {
    @PostMapping("/ReceivedTransferOtherBank.nh")
    fun transferOtherBank(
        @RequestBody
        transferOtherBankRequest: TransferOtherBankRequest,
        @RequestHeader("ApiNm")
        apiNm: String = "ReceivedTransferOtherBank",
        @RequestHeader("Istuno")
        istuno: String,
    ): NhBankingCommonResponse
}
