package com.cuberlabs.cuperpinserver.infrastructure.env.banking

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("banking.nh")
data class NhBankingProperties(
    val iscd: String,
    val fintechApsno: String,
    val apiSvcCd: String,
    val accessToken: String
)