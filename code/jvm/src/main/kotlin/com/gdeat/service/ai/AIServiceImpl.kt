package com.gdeat.service.ai

import com.gdeat.config.ExternalAIApiConfig
import com.gdeat.service.ai.config.models.AIRequest
import com.gdeat.service.ai.config.models.AIResponse
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient

@Service
@Transactional(rollbackFor = [Exception::class])
class AIServiceImpl(
    private val webClient: WebClient,
    private val config: ExternalAIApiConfig
) : AIService<AIRequest, AIResponse> {

    override suspend fun generateEntitiesAndRelations(request: AIRequest): AIResponse {
        val response = webClient.post()
            .uri(config.apiKey+config.endpoints.processRequest)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(AIResponse::class.java)
            .awaitFirst()

        return response
    }
}
