package com.cuberlabs.cuperpinserver.infrastructure.feign.store

import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request.GenerateTokenRequest
import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request.GetProductOrdersRequest
import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.response.GenerateTokenResponse
import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.response.GetProductOrdersResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "store",
    url = "https://api.commerce.naver.com/external"
)
interface StoreClient {
    @PostMapping("/v1/oauth2/token")
    fun generateToken(
        @RequestBody
        generateTokenRequest: GenerateTokenRequest
    ): GenerateTokenResponse

    @GetMapping("/v1/pay-order/seller/product-orders")
    fun getProductOrders(
        @RequestBody
        getProductOrdersRequest: GetProductOrdersRequest
    ): GetProductOrdersResponse
}