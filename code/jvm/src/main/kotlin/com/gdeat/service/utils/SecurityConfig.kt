package com.gdeat.service.utils

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun hashPassword(username: String, rawPassword: String): String {
        return passwordEncoder().encode(username + rawPassword)
    }

    fun hashToken(token: String): String{
        return passwordEncoder().encode(token)
    }

    fun verifyHashPassword(username: String, rawPassword: String, encodedPassword: String): Boolean {
        return passwordEncoder().matches(username + rawPassword, encodedPassword)
    }
}