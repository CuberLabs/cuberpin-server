package com.cuberlabs.cuperpinserver.infrastructure.websocket

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap

@Component
class ChargeSocketHandler : TextWebSocketHandler() {
    private val sessions = ConcurrentHashMap<String, MutableList<WebSocketSession>>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val rechargeId = getRechargeIdFromUri(session) ?: return
        sessions.computeIfAbsent(rechargeId) { mutableListOf() }.add(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Received message from ${session.id}: ${message.payload}")
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: org.springframework.web.socket.CloseStatus) {
        val rechargeId = getRechargeIdFromUri(session) ?: return
        sessions[rechargeId]?.remove(session)
        if (sessions[rechargeId].isNullOrEmpty()) {
            sessions.remove(rechargeId)
        }
    }

    fun notifyRechargeState(rechargeId: String, message: String) {
        sessions[rechargeId]?.forEach { session ->
            session.sendMessage(TextMessage(message))
        }
    }

    private fun getRechargeIdFromUri(session: WebSocketSession): String? {
        return session.uri?.path?.split("/")?.lastOrNull()
    }
}
