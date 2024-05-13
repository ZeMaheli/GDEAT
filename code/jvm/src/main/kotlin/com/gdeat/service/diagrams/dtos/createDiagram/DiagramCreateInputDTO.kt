package com.gdeat.service.diagrams.dtos.createDiagram

/**
 * A Graph Creation Input DTO.
 *
 * @property model model to be communicated
 * @property prompt message to be sent
 */
data class DiagramCreateInputDTO(
    val model: String,
    val prompt: String
)