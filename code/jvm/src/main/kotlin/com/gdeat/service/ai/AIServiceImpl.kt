package com.gdeat.service.ai

import com.fasterxml.jackson.databind.ObjectMapper
import com.gdeat.config.ExternalAIApiConfig
import com.gdeat.service.ai.config.models.AIRequest
import com.gdeat.service.ai.config.models.AIResponse
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.exceptions.AIServiceException
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient

@Service
@Transactional(rollbackFor = [Exception::class])
class AIServiceImpl(
    private val webClient: WebClient,
    private val config: ExternalAIApiConfig,
    private val objectMapper: ObjectMapper
) : AIService<AIRequest, DiagramCreateOutputDTO> {

    override suspend fun generateEntitiesAndRelations(request: AIRequest): DiagramCreateOutputDTO {
        val response = requestFromLLM(request)

        if (response.response.isEmpty()) {
            throw AIServiceException("Invalid response from LLM:")
        }

        return response.response.fromJsonToEntityRelationDiagramInfo()
    }

    private fun String.fromJsonToEntityRelationDiagramInfo(): DiagramCreateOutputDTO {
        return try {
            objectMapper.readValue(this, DiagramCreateOutputDTO::class.java)
        } catch (ex: Exception) {
            throw AIServiceException("Invalid JSON response from LLM: $this")
        }
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
