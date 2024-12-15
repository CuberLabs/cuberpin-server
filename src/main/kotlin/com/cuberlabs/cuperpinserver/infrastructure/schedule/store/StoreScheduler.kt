package com.cuberlabs.cuperpinserver.infrastructure.schedule.store

import com.cuberlabs.cuperpinserver.domain.store.StoreSchedulerService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class StoreScheduler(
    private val storeSchedulerService: StoreSchedulerService
) {
    @Scheduled(fixedRate = 3000)
    fun scheduleTask() {
        println("스케줄러 실행 중: ${System.currentTimeMillis()}")
        storeSchedulerService.sendGiftCardMessage()
    }
}