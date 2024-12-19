package com.cuberlabs.cuperpinserver.domain.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "validation-code", timeToLive = 300)
class UserValidationCheck(
    phoneNumber: String,
    name: String,
    validationCode: Int,
    isValid: Boolean
) {
    @Id
    var phoneNumber: String = phoneNumber
        protected set

    var name: String = name
        protected set

    var validationCode: Int = validationCode
        protected set

    var isValid: Boolean = isValid
        protected set

    fun validationsucceed() {
        this.isValid = true
    }
}
