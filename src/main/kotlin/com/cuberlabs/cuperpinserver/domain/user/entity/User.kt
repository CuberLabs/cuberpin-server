package com.cuberlabs.cuperpinserver.domain.user.entity

import com.cuberlabs.cuperpinserver.domain.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class User(
    id: UUID?,
    name: String,
    password: String,
    phoneNumber: String
): BaseUUIDEntity(id) {
    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "password", nullable = false)
    var password: String = password
        protected set

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String = phoneNumber
        protected set
}
