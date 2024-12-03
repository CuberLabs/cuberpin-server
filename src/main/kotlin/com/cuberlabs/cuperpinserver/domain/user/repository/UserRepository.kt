package com.cuberlabs.cuperpinserver.domain.user.repository

import com.cuberlabs.cuperpinserver.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
}
