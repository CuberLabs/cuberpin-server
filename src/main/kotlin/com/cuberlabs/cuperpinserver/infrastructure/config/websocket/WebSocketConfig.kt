package com.cuberlabs.cuperpinserver.infrastructure.config.websocket

import com.cuberlabs.cuperpinserver.infrastructure.websocket.ChargeSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val chargeSocketHandler: ChargeSocketHandler
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(chargeSocketHandler, "/ws/charge/{id}").setAllowedOrigins("*")
    }
}