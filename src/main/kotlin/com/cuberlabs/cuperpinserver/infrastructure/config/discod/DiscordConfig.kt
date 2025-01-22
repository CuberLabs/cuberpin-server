package com.cuberlabs.cuperpinserver.infrastructure.config.discod

import discord4j.core.DiscordClientBuilder
import discord4j.core.GatewayDiscordClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class DiscordConfig {
    @Value("\${discord.bot-token}")
    var botToken: String = ""

    @Bean
    fun client(): GatewayDiscordClient {
        return DiscordClientBuilder.create(botToken)
            .build()
            .login()
            .block() ?: throw RuntimeException("Failed to connect to DiscordClient")
    }
}