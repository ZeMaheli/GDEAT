package com.gdeat.service.diagrams.dtos.storeDiagram

/**
 * A Diagram Store Input DTO.
 *
 * @property Entities an object with entities names and their attributes
 * @property Relations an object with the different relations between the entities
 */
data class StoreDiagramInputDTO(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
)