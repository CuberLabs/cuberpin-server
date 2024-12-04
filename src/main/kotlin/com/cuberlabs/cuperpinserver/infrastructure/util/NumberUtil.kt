package com.cuberlabs.cuperpinserver.infrastructure.util

import kotlin.random.Random

object NumberUtil {
    fun generateRandomNumber(): Int {
        return Random.nextInt(100000, 1000000)
    }
}
