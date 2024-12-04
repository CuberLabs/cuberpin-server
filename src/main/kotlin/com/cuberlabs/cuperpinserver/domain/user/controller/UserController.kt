package com.cuberlabs.cuperpinserver.domain.user.controller

import com.cuberlabs.cuperpinserver.domain.user.controller.dto.request.CodeValidationRequest
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.request.LoginRequest
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.request.SendValidationCodeRequest
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.request.SignupRequest
import com.cuberlabs.cuperpinserver.domain.user.controller.dto.response.TokenResponse
import com.cuberlabs.cuperpinserver.domain.user.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/send-validation-code")
    fun sendValidationCode(req: SendValidationCodeRequest) {
        userService.sendCode(req)
    }

    @PostMapping("/validation-code")
    fun validationCode(req: CodeValidationRequest) {
        userService.validationCode(req)
    }

    @PostMapping("/signup")
    fun signup(signupRequest: SignupRequest): TokenResponse {
        return userService.signup(signupRequest)
    }

    @PostMapping("/login")
    fun login(loginRequest: LoginRequest): TokenResponse {
        return userService.login(loginRequest)
    }
}
