package com.cuberlabs.cuperpinserver.infrastructure.config.security

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsUtils

@Component
class RequestPermitConfig : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.authorizeRequests().run {
            antMatchers("/charges").permitAll()
            antMatchers("/gift-card/status/**").permitAll()
            antMatchers("/users/**").permitAll()
            antMatchers("/ws/charge/{id}").permitAll()
            anyRequest().authenticated()
        }
    }
}
