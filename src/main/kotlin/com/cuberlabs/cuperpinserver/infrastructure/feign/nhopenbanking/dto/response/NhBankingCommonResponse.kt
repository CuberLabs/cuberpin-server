package com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class NhBankingCommonResponse(
    @JsonProperty("ApiNm")
    val apiName: String,

    @JsonProperty("Tsymd")
    val transmissionDate: String, // YYYYMMDD format

    @JsonProperty("Trtm")
    val transmissionTime: String, // HHMMSS format

    @JsonProperty("Iscd")
    val institutionCode: String,

    @JsonProperty("FintechApsno")
    val fintechAppSerialNumber: String,

    @JsonProperty("APISvcCd")
    val apiServiceCode: String,

    @JsonProperty("Istuno")
    val uniqueTransactionNumber: String,

    @JsonProperty("Rpcd")
    val responseCode: String,

    @JsonProperty("Rsms")
    val responseMessage: String
)
