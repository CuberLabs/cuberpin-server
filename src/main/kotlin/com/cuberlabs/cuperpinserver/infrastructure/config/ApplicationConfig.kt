package com.cuberlabs.cuperpinserver.infrastructure.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = ["com.cuberlabs.cuperpinserver"]
)
@ConfigurationPropertiesScan(basePackages = ["com.cuberlabs.cuperpinserver"])
class ApplicationConfig
