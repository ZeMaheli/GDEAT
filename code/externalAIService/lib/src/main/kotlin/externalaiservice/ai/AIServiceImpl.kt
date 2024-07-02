package externalaiservice.ai

import externalaiservice.ai.config.models.AIResponse
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import externalaiservice.ai.config.ExternalAIApiConfig
import externalaiservice.ai.config.models.AIRequest
import externalaiservice.exceptions.AIServiceException

@Service
class AIServiceImpl(
    private val webClient: WebClient,
    private val config: ExternalAIApiConfig
) : AIService<AIRequest, AIResponse> {

    override suspend fun generateEntitiesAndRelations(request: AIRequest): AIResponse {
        val response = requestFromLLM(request)

        if (response.response.isEmpty()) {
            throw AIServiceException("Invalid response from LLM:")
        }

        return response
    }

    private suspend fun requestFromLLM(request: AIRequest): AIResponse {
        return try {
            webClient.post()
                .uri(config.endpoints.processRequest)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AIResponse::class.java)
                .awaitFirst()
        } catch (ex: Exception) {
            throw AIServiceException("Error communicating with LLM")
        }
    }
}
