package com.cuberlabs.cuperpinserver.domain.sms

object SmsMessages {
    fun validationCode(code: Int): String {
        return """
            쿠버핀 인증번호 [${code}]
            타인에게 절대 알리지 마세요.
        """.trimIndent()
    }

    fun depositRequest(bank: String, accountNumber: String, amount: String): String {
        return """
            상품권 충전이 완료되어 입금이 필요합니다.
            ${bank} ${accountNumber} ${ amount }
        """.trimIndent()
    }
}
