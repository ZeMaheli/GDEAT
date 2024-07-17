package server.externalAI.service.ai

import server.externalAI.service.ai.models.AIResponse
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import server.externalAI.config.ExternalAIApiConfig
import server.externalAI.service.ai.models.AIRequest
import org.springframework.web.reactive.function.client.WebClient
import server.externalAI.service.exceptions.AIServiceException

/**
 * Implementation of the AIService interface, providing methods to generate entities and relations using an AI model.
 *
 * @property webClient The WebClient instance used for making HTTP requests.
 * @property config The configuration for the external AI API.
 */
@Service
class AIServiceImpl(
    private val webClient: WebClient,
    private val config: ExternalAIApiConfig
) : AIService<AIRequest, AIResponse> {

    override suspend fun processRequest(request: AIRequest): AIResponse {
        val aiResponse = generateEntitiesAndRelations(request)

        if (aiResponse.response.isEmpty()) {
            throw AIServiceException("Invalid response from LLM")
        }

        return aiResponse
    }

    /**
     * Sends the request to the external AI model and retrieves the response.
     *
     * This function makes an HTTP POST request to the AI model's endpoint with the given request.
     * If there is an error during communication, an exception is thrown.
     *
     * @param request The request object containing the input data for the AI model.
     * @return The response object from the AI model.
     * @throws AIServiceException If there is an error during communication with the AI model.
     */
    private suspend fun generateEntitiesAndRelations(request: AIRequest): AIResponse {
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
