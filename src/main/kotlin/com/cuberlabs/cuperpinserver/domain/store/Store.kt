package com.cuberlabs.cuperpinserver.domain.store

interface Store {
    fun getOrderIdsByFilter(
        from: String,
        to: String,
        rangeType: String,
        productOrderStatus: String,
        claimStatuses: String,
        placeOrderStatusType: String,
        fulfillment: String,
        pageSize: String,
        page: String
    ): List<String>
}