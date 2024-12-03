package com.cuberlabs.cuperpinserver.infrastructure.security

import com.cuberlabs.cuperpinserver.domain.user.entity.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import com.cuberlabs.cuperpinserver.infrastructure.security.principle.CustomUserDetails

@Component
internal class SecurityUtil() {
    fun getCurrentUser(): User {
        return (SecurityContextHolder.getContext().authentication.principal as CustomUserDetails).user
    }
}
