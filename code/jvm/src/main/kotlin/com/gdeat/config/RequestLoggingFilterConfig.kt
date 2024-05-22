package com.gdeat.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter

/**
 * Configuration for the request logging filter.
 */
@Configuration
class RequestLoggingFilterConfig {

    /**
     * Creates a request logging filter.
     *
     * @return the request logging filter
     */
    @Bean
    fun logFilter() = CommonsRequestLoggingFilter().also {
        it.setIncludeClientInfo(true)
        it.setIncludeQueryString(true)
        it.setIncludePayload(true)
        it.setIncludeHeaders(true)
        it.setMaxPayloadLength(10000)
        it.setAfterMessagePrefix("REQUEST DATA : ")
    }
}