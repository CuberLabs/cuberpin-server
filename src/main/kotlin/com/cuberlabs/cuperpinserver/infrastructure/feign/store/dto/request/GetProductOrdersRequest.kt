package com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GetProductOrdersRequest(
    var from: String,
    var to: String,
    var rangeType: String,
    var productOrderStatuses: String?,
    var claimStatuses: String?,
    var placeOrderStatusType: String?,
    var fulfillment: String?,
    var pageSize: Int,
    var page: Int
)
