package com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request

data class GetProductOrdersRequest(
    val from: String,
    val to: String,
    val rangeType: String,
    val productOrderStatuses: String,
    val claimStatuses: String,
    val placeOrderStatusType: String,
    val fulfillment: String,
    val pageSize: String,
    val page: String
)
