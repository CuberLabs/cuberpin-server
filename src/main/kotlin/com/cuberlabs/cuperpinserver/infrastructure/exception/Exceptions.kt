package com.cuberlabs.cuperpinserver.infrastructure.exception

open class CuberException(
    val status: Int,
    message: String
) : RuntimeException(message) {

    companion object {

        // Default
        val BAD_REQUEST = CuberException(400, "Bad Request")
        val UNAUTHORIZED = CuberException(401, "Unauthorized")
        val FORBIDDEN = CuberException(403, "Forbidden")
        val NOT_FOUND = CuberException(404, "Not Found")
        val CONFLICT = CuberException(409, "Conflict")
        protected val I_M_A_TEAPOT = CuberException(418, "I'm a Teapot")
        val INTERNAL_SERVER_ERROR = CuberException(500, "Internal Server Error")
    }
}

class BusinessLogicException(
    status: Int,
    message: String
) : CuberException(status, message) {
    constructor(errorCodePrefixSuffix: ErrorCodePrefixSuffix, domainName: DomainNames) :
        this(
            status = errorCodePrefixSuffix.status,
            message = if (errorCodePrefixSuffix.isPrefix) {
                errorCodePrefixSuffix.message + domainName.value
            } else {
                domainName.value + errorCodePrefixSuffix.message
            }
        )

    companion object {
        val USER_ALREADY_EXISTS = BusinessLogicException(ErrorCodePrefixSuffix.ALREADY_EXISTS_XXX, DomainNames.USER)
        val USER_NOT_FOUND = BusinessLogicException(ErrorCodePrefixSuffix.XXX_NOT_FOUND, DomainNames.USER)
        val GIFT_CARD_NOT_FOUND = BusinessLogicException(ErrorCodePrefixSuffix.XXX_NOT_FOUND, DomainNames.GIFT_CARD)
        val GIFT_CARD_CHARGE_NOT_FOUND = BusinessLogicException(ErrorCodePrefixSuffix.XXX_NOT_FOUND, DomainNames.GIFT_CARD_CHARGE)
        val USER_VALIDATION_UNAUTHORIZED = BusinessLogicException(ErrorCodePrefixSuffix.XXX_PERMISSION_DENIED, DomainNames.USER)
    }
}

class SecurityException(
    status: Int,
    message: String
) : CuberException(status, message) {

    constructor(errorCodePrefixSuffix: ErrorCodePrefixSuffix, domainName: DomainNames) :
        this(
            status = errorCodePrefixSuffix.status,
            message = if (errorCodePrefixSuffix.isPrefix) {
                errorCodePrefixSuffix.message + domainName.value
            } else {
                domainName.value + errorCodePrefixSuffix.message
            }
        )

    companion object {

        val INVALID_TOKEN = SecurityException(401, "Invalid Token")

        val PERMISSION_DENIED = SecurityException(403, "Permission Denied")
    }
}

enum class ErrorCodePrefixSuffix(
    val status: Int,
    val message: String,
    val isPrefix: Boolean
) {
    XXX_NOT_FOUND(404, " Not Found", false),
    XXX_PERMISSION_DENIED(403, " Permission Denied", false),
    XXX_BAD_REQUEST(400, " Bad Request", false),
    ALREADY_EXISTS_XXX(409, "Already Exists ", true)
}

class FilterException(
    status: Int,
    message: String
) : CuberException(status, message)

class InterceptorException(
    status: Int,
    message: String
) : CuberException(status, message)

class PresentationValidationException(
    status: Int,
    message: String,
    val fields: Map<String, String>
) : CuberException(status, message)

class AuthenticationException(
    status: Int,
    message: String
) : CuberException(status, message) {

    companion object {

        // UnAuthorized
        val INVALID_TOKEN = AuthenticationException(401, "Invalid Token")
        val EXPIRED_TOKEN = AuthenticationException(401, "Expired Token")
        val UNAUTHORIZED = AuthenticationException(401, "Unauthorized")
    }
}

class FeignException(
    status: Int,
    message: String
) : CuberException(status, message) {

    companion object {

        val FEIGN_BAD_REQUEST = FeignException(400, "Feign Bad Request")
        val FEIGN_UNAUTHORIZED = FeignException(401, "Feign UnAuthorized")
        val FEIGN_FORBIDDEN = FeignException(403, "Feign Forbidden")
        val FEIGN_SERVER_ERROR = FeignException(500, "Feign Server Error")
        val FEIGN_UNKNOWN_CLIENT_ERROR = FeignException(500, "Feign Unknown Error")
    }
}

class CriticalException(
    status: Int,
    message: String
) : CuberException(status, message)
