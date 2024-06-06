package com.gdeat.service.ai.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.gdeat.config.ExternalAIApiConfig
import com.gdeat.service.ai.AIService
import com.gdeat.service.ai.AIServiceImpl
import com.gdeat.service.ai.config.models.AIRequest
import com.gdeat.service.ai.config.models.EntityRelationDiagramInfo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class AIServiceConfig {

    @Bean
    fun aiService(
        config: ExternalAIApiConfig,
        webClient: WebClient,
        objectMapper: ObjectMapper
    ): AIService<AIRequest, EntityRelationDiagramInfo> {
        return AIServiceImpl(webClient, config, objectMapper)
    }
}
