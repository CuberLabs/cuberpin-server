package com.cuberlabs.cuperpinserver.domain.user.repository

import com.cuberlabs.cuperpinserver.domain.user.entity.UserValidationCheck
import org.springframework.data.jpa.repository.JpaRepository

interface UserValCheckRepository : JpaRepository<UserValidationCheck, String> {
    fun findByPhoneNumber(phoneNumber: String): UserValidationCheck?
}
