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
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.transaction.Transactional

@Component
class UserService(
    private val jwtService: JwtService,
    private val userValCheckRepository: UserValCheckRepository,
    private val userRepository: UserRepository,
    private val sms: Sms,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
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
        val userValidationCheck = userValCheckRepository.findByIdOrNull(req.phoneNumber)

        userValidationCheck?.let {
            if(it.validationCode == req.validationCode) {
                it.validationsucceed()
            } else {
                throw AuthenticationException.UNAUTHORIZED
            }
        } ?: throw AuthenticationException.UNAUTHORIZED

        userValCheckRepository.save(userValidationCheck)
    }

    fun signup(req: SignupRequest): TokenResponse {
        userRepository.findByPhoneNumber(req.phoneNumber)?.let {
            throw BusinessLogicException.USER_ALREADY_EXISTS
        }
        val validationCheck = userValCheckRepository.findByIdOrNull(req.phoneNumber) ?: throw AuthenticationException.UNAUTHORIZED

        if(!validationCheck.isValid) {
            throw AuthenticationException.UNAUTHORIZED
        }

        val user = req.run {
            userRepository.save(
                User(
                    id = UUID.randomUUID(),
                    name = name,
                    password = passwordEncoder.encode(password),
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
        if(!passwordEncoder.matches(req.password, user.password)) {
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
