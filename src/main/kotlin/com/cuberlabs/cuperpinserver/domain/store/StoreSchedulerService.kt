package com.cuberlabs.cuperpinserver.domain.store

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class StoreSchedulerService(
    private val store: Store
) {
    fun sendGiftCardMessage() {
        val orderIds = store.getOrderIdsByFilter(
            from = formatDateTime(LocalDateTime.now()),
            to = formatDateTime(LocalDateTime.now().plusHours(23)),
            rangeType = "PAYED_DATETIME",
            productOrderStatus = "PAYED",
            page = 1,
            pageSize = 300
        )

        println("에잇! $orderIds")
    }
    fun formatDateTime(time: LocalDateTime): String {
        val zonedDateTime = time.atZone(ZoneId.of("Asia/Seoul"))
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        return zonedDateTime.format(formatter)
    }
}