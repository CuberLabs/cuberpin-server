package com.cuberlabs.cuperpinserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@EnableFeignClients
@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
class CuberBackendApplication

fun main(args: Array<String>) {
    runApplication<CuberBackendApplication>(*args)
}
