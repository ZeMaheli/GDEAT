package com.gdeat.http.controllers.diagrams.models.createDiagram

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO

/**
 * A Diagram Creation Input Model.
 *
 * @property prompt the message to be sent
 */
data class DiagramCreateInputModel(
    val prompt: String
) {

    /**
     * Converts this model to a service DTO.
     *
     * @return the service DTO
     */
    fun toGraphCreateDTO() = DiagramCreateInputDTO(
        prompt = prompt
    )
}