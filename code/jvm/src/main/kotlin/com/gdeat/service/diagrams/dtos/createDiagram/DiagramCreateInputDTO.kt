package com.gdeat.service.diagrams.dtos.createDiagram

import com.gdeat.service.diagrams.aiserver.AIServerRequest

/**
 * A Diagram Creation Input DTO.
 *
 * @property prompt message to be sent
 */
data class DiagramCreateInputDTO(
    val prompt: String
){
    fun toAiServerRequest() = AIServerRequest(prompt)
}
