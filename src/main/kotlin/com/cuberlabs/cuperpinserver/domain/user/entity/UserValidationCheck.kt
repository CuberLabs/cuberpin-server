package com.cuberlabs.cuperpinserver.domain.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
class UserValidationCheck(
    @Id
    val phoneNumber: String,
    val name: String,
    val ttl: Long = 300,
    val validationCode: Int,
    var isValid: Boolean
) {
    fun validationsucceed() {
        this.isValid = true
    }
}
