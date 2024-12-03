package com.cuberlabs.cuperpinserver.infrastructure.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.cuberlabs.cuperpinserver.infrastructure.error.filter.ErrorLogResponseFilter
import com.cuberlabs.cuperpinserver.infrastructure.error.filter.ExceptionConvertFilter
import com.cuberlabs.cuperpinserver.infrastructure.security.jwt.JwtFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.context.SecurityContextHolderFilter
import org.springframework.stereotype.Component

@Component
class SecurityFilterChainConfig(
    private val jwtFilter: JwtFilter,
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.run {
            addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            addFilterBefore(ExceptionConvertFilter(), SecurityContextHolderFilter::class.java)
            addFilterBefore(ErrorLogResponseFilter(objectMapper), ExceptionConvertFilter::class.java)
        }
    }
}
