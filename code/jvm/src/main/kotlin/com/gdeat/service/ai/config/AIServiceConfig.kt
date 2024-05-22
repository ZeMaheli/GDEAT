package com.gdeat.service.ai.config

import com.gdeat.config.ExternalAIApiConfig
import com.gdeat.service.ai.AIService
import com.gdeat.service.ai.AIServiceImpl
import com.gdeat.service.ai.config.models.AIRequest
import com.gdeat.service.ai.config.models.AIResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class AIServiceConfig {

    @Bean
    fun aiService(config: ExternalAIApiConfig, webClient: WebClient): AIService<AIRequest, AIResponse> {
        return AIServiceImpl(webClient, config)
    }
}
