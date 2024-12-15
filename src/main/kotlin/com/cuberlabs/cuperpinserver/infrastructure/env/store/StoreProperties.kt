package com.cuberlabs.cuperpinserver.infrastructure.env.store

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(value = "store")
data class StoreProperties(
    val clientId: String,
    val clientSecret: String,
)