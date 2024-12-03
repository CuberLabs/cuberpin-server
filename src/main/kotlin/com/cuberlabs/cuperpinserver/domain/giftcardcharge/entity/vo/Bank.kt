package com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo

enum class Bank(val code: String, val fullName: String) {
    KB_BANK("097", "KB국민은행"),
    SHINHAN_BANK("088", "신한은행"),
    HANA_BANK("081", "하나은행"),
    WOORI_BANK("020", "우리은행"),
    NH_BANK("011", "NH농협은행"),
    SUHYUP_BANK("007", "Sh수협은행"),
    INDUSTRIAL_BANK("002", "한국산업은행"),
    IBK_BANK("003", "IBK기업은행"),
    CITI_BANK("027", "한국씨티은행"),
    K_BANK("089", "케이뱅크"),
    KAKAO_BANK("090", "카카오뱅크"),
    TOSS_BANK("092", "토스뱅크"),
    SAEMAUL_BANK("045", "새마을금고"),
    SINHYUP_BANK("048", "신협"),
    SAVINGS_BANK("050", "저축은행중앙회"),
    POST_OFFICE("071", "우체국"),
    DGB_BANK("031", "DGB대구은행"),
    BNK_BUSAN_BANK("032", "BNK부산은행"),
    GWANGJU_BANK("034", "광주은행"),
    JEJU_BANK("035", "제주은행"),
    JB_BANK("037", "JB전북은행"),
    BNK_GYEONGNAM_BANK("039", "BNK경남은행");

    companion object {
        fun getByCode(code: String): Bank? {
            return entries.find { it.code == code }
        }

        fun getByFullName(fullName: String): Bank? {
            return entries.find { it.fullName == fullName }
        }
    }
}
