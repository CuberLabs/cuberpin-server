package com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class TransferOtherBankRequest(
    @JsonProperty("Bncd")
    val bncd: String,
    @JsonProperty("Acno")
    val acno: String,
    @JsonProperty("Tram")
    val tram: String,
    @JsonProperty("DractOtlt")
    val dractOtlt: String,
    @JsonProperty("MractOtlt")
    val mractOtlt: String,
)
