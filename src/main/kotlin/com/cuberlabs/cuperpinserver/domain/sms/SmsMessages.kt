package com.cuberlabs.cuperpinserver.domain.sms

object SmsMessages {
    fun validationCode(code: Int): String {
        return """
            쿠버핀 인증번호 [${code}]
            타인에게 절대 알리지 마세요.
        """.trimIndent()
    }
}
