package com.gdeat.http.controllers.diagrams.models.storeDiagram

import com.gdeat.service.diagrams.dtos.storeDiagram.StoreDiagramInputDTO


/**
 * A Diagram Store Input Model.
 *
 * @property Entities an object with entities names and their attributes
 * @property Relations an object with the different relations between the entities
 */
data class StoreDiagramInputModel(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
) {
    /**
     * Converts this model to a service DTO.
     *
     * @return the service DTO
     */
    fun toDiagramStoreDTO() = StoreDiagramInputDTO(
        Entities = Entities,
        Relations = Relations
    )
}