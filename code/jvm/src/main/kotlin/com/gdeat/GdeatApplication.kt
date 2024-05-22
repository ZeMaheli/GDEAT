package com.gdeat

import com.gdeat.http.pipeline.authentication.AuthInterceptor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * The main entry point of the Spring application.
 *
 * @property authInterceptor the authentication interceptor
 */
@SpringBootApplication
class GdeatApplication(
    val authInterceptor: AuthInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedOrigins("http://localhost:9000", "http://localhost")
    }
}

fun main(args: Array<String>) {
    runApplication<GdeatApplication>(*args)
}
