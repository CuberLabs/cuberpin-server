package com.cuberlabs.cuperpinserver.infrastructure.exception

enum class DomainNames(
    val value: String,
    val lowCaseValue: String
) {
    USER("User", "user"),
    GIFT_CARD("GiftCard", "gift_card"),
    GIFT_CARD_CHARGE("GiftCardCharge", "gift_card_charge")
}
