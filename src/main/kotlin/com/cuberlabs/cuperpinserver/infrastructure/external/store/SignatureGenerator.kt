package com.cuberlabs.cuperpinserver.infrastructure.external.store

import org.apache.commons.lang3.StringUtils
import org.mindrot.jbcrypt.BCrypt
import java.nio.charset.StandardCharsets
import java.util.Base64

object SignatureGenerator {
    fun generateHashedPassword(clientId: String, timestamp: String, clientSecret: String): String {
        val password = StringUtils.joinWith("_", clientId, timestamp)

        val hashedPw = BCrypt.hashpw(password, clientSecret)

        return Base64.getUrlEncoder().encodeToString(hashedPw.toByteArray(StandardCharsets.UTF_8))
    }
}