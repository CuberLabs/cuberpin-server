package com.cuberlabs.cuperpinserver.infrastructure.config.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.cuberlabs.cuperpinserver.infrastructure.exception.CriticalException
import com.cuberlabs.cuperpinserver.infrastructure.error.filter.ErrorLogResponseFilter
import com.cuberlabs.cuperpinserver.infrastructure.error.filter.ExceptionConvertFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.web.filter.DelegatingFilterProxy
import javax.servlet.Filter

class FilterChainConfig(
    private val objectMapper: ObjectMapper
) {
    private val filtersOrderList = listOf<Class<out Filter>>(
        ErrorLogResponseFilter::class.java,
        ExceptionConvertFilter::class.java,
        DelegatingFilterProxy::class.java
    )

    fun errorLogResponseFilterConfig(): FilterRegistrationBean<ErrorLogResponseFilter> {
        val bean = FilterRegistrationBean(
            ErrorLogResponseFilter(objectMapper)
        )

        bean.addUrlPatterns("/*")
        bean.order = getFilterOrder(ErrorLogResponseFilter::class.java)

        return bean
    }

    // @Bean
    fun exceptionConvertFilterConfig(): FilterRegistrationBean<ExceptionConvertFilter> {
        val bean = FilterRegistrationBean(
            ExceptionConvertFilter()
        )

        bean.addUrlPatterns("/*")
        bean.order = getFilterOrder(ExceptionConvertFilter::class.java)

        return bean
    }

    fun springSecurityFilterChainOrderConfig(): FilterRegistrationBean<DelegatingFilterProxy> {
        val bean = FilterRegistrationBean(
            DelegatingFilterProxy("springSecurityFilterChain")
        )

        bean.order = getFilterOrder(DelegatingFilterProxy::class.java)

        return bean
    }

    private fun getFilterOrder(`class`: Class<out Filter>): Int {
        val index = this.filtersOrderList.indexOf(`class`)

        if (index == -1) {
            throw CriticalException(500, "Attempted registration of Unknown filter")
        }

        return index
    }
}
