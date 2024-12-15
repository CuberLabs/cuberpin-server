package com.cuberlabs.cuperpinserver.infrastructure.feign.store

import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request.GetProductOrdersRequest
import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.response.GenerateTokenResponse
import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.response.GetProductOrdersResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(
    name = "store",
    url = "https://api.commerce.naver.com/external"
)
interface StoreClient {
    @PostMapping("/v1/oauth2/token")
    fun generateToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("timestamp") timestamp: Long,
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_secret_sign") clientSecretSign: String,
        @RequestParam("type") type: String,
        @RequestHeader("Content-Type") contentType: String = "application/x-www-form-urlencoded",
    ): GenerateTokenResponse

    @GetMapping("/v1/pay-order/seller/product-orders")
    fun getProductOrders(
        @RequestParam("from") from: String,
        @RequestParam("to") to: String,
        @RequestParam("rangeType") rangeType: String,
        @RequestParam("productOrderStatuses") productOrderStatuses: String?,
        @RequestParam("claimStatuses") claimStatuses: String?,
        @RequestParam("placeOrderStatusType") placeOrderStatusType: String?,
        @RequestParam("fulfillment") fulfillment: String?,
        @RequestParam("pageSize") pageSize: Int,
        @RequestParam("page") page: Int,
        @RequestHeader("Authorization") authorizationHeader: String
    ): GetProductOrdersResponse
}