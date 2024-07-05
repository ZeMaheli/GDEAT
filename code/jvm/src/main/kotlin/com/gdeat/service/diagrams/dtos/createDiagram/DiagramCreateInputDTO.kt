package com.gdeat.service.diagrams.dtos.createDiagram

import externalaiservice.ai.config.models.AIRequest

/**
 * A Diagram Creation Input DTO.
 *
 * @property prompt message to be sent
 */
data class DiagramCreateInputDTO(
    val prompt: String
) {
    fun toAIRequest(): AIRequest {
        return AIRequest(
            prompt = this.prompt
        )
    }
}
