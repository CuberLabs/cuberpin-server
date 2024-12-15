package com.cuberlabs.cuperpinserver.infrastructure.feign.nhopenbanking.config

import com.cuberlabs.cuperpinserver.infrastructure.env.banking.NhBankingProperties
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class NhBankingCommonRequest(
    private val nhBankingProperties: NhBankingProperties
) {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template: RequestTemplate ->
            val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"))

            // 헤더 설정
            template.header("Tsymd", currentDate)
            template.header("Trtm", currentTime)
            template.header("Iscd", nhBankingProperties.iscd)
            template.header("FintechApsno", nhBankingProperties.fintechApsno)
            template.header("APISvcCd", nhBankingProperties.apiSvcCd)
            template.header("AccessToken", nhBankingProperties.accessToken)
        }
    }
}
