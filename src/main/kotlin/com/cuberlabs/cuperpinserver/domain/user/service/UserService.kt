package com.cuberlabs.cuperpinserver.domain.user.service

import com.cuberlabs.cuperpinserver.domain.sms.Sms
import com.cuberlabs.cuperpinserver.domain.sms.SmsMessages
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.request.CodeValidationRequest
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.request.LoginRequest
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.request.SendValidationCodeRequest
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.request.SignupRequest
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.response.TokenResponse
import com.cuberlabs.cuperpinserver.domain.user.entity.User
import com.cuberlabs.cuperpinserver.domain.user.entity.UserValidationCheck
import com.cuberlabs.cuperpinserver.domain.user.repository.UserRepository
import com.cuberlabs.cuperpinserver.domain.user.repository.UserValCheckRepository
import com.cuberlabs.cuperpinserver.infrastructure.exception.AuthenticationException
import com.cuberlabs.cuperpinserver.infrastructure.exception.BusinessLogicException
import com.cuberlabs.cuperpinserver.infrastructure.security.jwt.JwtService
import com.cuberlabs.cuperpinserver.infrastructure.util.NumberUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.transaction.Transactional

@Component
class UserService(
    private val jwtService: JwtService,
    private val userValCheckRepository: UserValCheckRepository,
    private val userRepository: UserRepository,
    private val sms: Sms,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun sendCode(req: SendValidationCodeRequest) {
        val validationCode = NumberUtil.generateRandomNumber()
        userValCheckRepository.save(UserValidationCheck(
            phoneNumber = req.phoneNumber,
            name = req.name,
            validationCode = validationCode,
            isValid = false
        ))
        sms.sendMessage(req.phoneNumber, SmsMessages.validationCode(validationCode))
    }

    @Transactional
    fun validationCode(req: CodeValidationRequest) {
        val userValidationCheck = req.run {
            userValCheckRepository.findByPhoneNumberAndValidationCode(
                phoneNumber = phoneNumber,
                validationCode = validationCode
            )
        }
        userValidationCheck?.let {
            it.validationsucceed()
        } ?: throw AuthenticationException.UNAUTHORIZED
    }

    fun signup(req: SignupRequest): TokenResponse {
        val validationCheck = req.run {
            userValCheckRepository.findByIdOrNull(req.phoneNumber) ?: throw AuthenticationException.UNAUTHORIZED
        }

        if(!validationCheck.isValid) {
            throw AuthenticationException.UNAUTHORIZED
        }

        val user = req.run {
            userRepository.save(
                User(
                    id = UUID.randomUUID(),
                    name = name,
                    password = bCryptPasswordEncoder.encode(password),
                    phoneNumber = phoneNumber
                )
            )
        }

        return jwtService.generateTokens(user.phoneNumber).run {
            TokenResponse(
                accessToken = first,
                refreshToken = second
            )
        }
    }

    fun login(req: LoginRequest): TokenResponse {
        val user = userRepository.findByPhoneNumber(req.phoneNumber) ?: throw BusinessLogicException.USER_NOT_FOUND
        if(!bCryptPasswordEncoder.matches(req.password, user.password)) {
            throw AuthenticationException.UNAUTHORIZED
        }

        return jwtService.generateTokens(user.phoneNumber).run {
            TokenResponse(
                accessToken = first,
                refreshToken = second
            )
        }
    }
}
