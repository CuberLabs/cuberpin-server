package com.cuberlabs.cuperpinserver.infrastructure.external.store

import com.cuberlabs.cuperpinserver.domain.store.Store
import com.cuberlabs.cuperpinserver.infrastructure.env.store.StoreProperties
import com.cuberlabs.cuperpinserver.infrastructure.feign.store.StoreClient
import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request.GenerateTokenRequest
import com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.request.GetProductOrdersRequest
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeProperties: StoreProperties,
    private val storeClient: StoreClient
): Store {
    private fun generateOAuthToken(): String {
        val currentTimeMillis = System.currentTimeMillis()

        val response = storeClient.generateToken(
            GenerateTokenRequest(
                clientSecret = SignatureGenerator.generateHashedPassword(
                    clientSecret = storeProperties.clientSecret,
                    clientId = storeProperties.clientId,
                    timestamp = currentTimeMillis.toString()
                ),
                clientId = storeProperties.clientId,
                grantType = "client_credentials",
                timestamp = currentTimeMillis.toString(),
                type = "SELF"
            )
        )

        return response.accessToken
    }

    override fun getOrderIdsByFilter(
        from: String,
        to: String,
        rangeType: String,
        productOrderStatus: String,
        claimStatuses: String,
        placeOrderStatusType: String,
        fulfillment: String,
        pageSize: String,
        page: String
    ): List<String> {
        val response = storeClient.getProductOrders(
            GetProductOrdersRequest(
                from = from,
                to = to,
                rangeType = rangeType,
                productOrderStatuses = productOrderStatus,
                claimStatuses = claimStatuses,
                placeOrderStatusType = placeOrderStatusType,
                fulfillment = fulfillment,
                pageSize = pageSize,
                page = page
            )
        )

        return response.data.contents.map { it.productOrderId }
    }
}