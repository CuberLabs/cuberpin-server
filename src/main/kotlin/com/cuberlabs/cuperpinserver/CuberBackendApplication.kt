package com.cuberlabs.cuperpinserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class CuberBackendApplication

fun main(args: Array<String>) {
    runApplication<CuberBackendApplication>(*args)
}
