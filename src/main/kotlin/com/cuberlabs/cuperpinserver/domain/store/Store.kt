package com.cuberlabs.cuperpinserver.domain.store

interface Store {
    fun getOrderIdsByFilter(
        from: String,
        to: String,
        rangeType: String,
        productOrderStatus: String? = null,
        claimStatuses: String? = null,
        placeOrderStatusType: String? = null,
        fulfillment: String? = null,
        pageSize: Int,
        page: Int
    ): List<String>
}