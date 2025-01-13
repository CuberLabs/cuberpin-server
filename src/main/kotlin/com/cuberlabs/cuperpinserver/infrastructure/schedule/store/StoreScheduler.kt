package com.cuberlabs.cuperpinserver.infrastructure.schedule.store

import com.cuberlabs.cuperpinserver.domain.store.StoreSchedulerService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class StoreScheduler(
    private val storeSchedulerService: StoreSchedulerService
) {
}