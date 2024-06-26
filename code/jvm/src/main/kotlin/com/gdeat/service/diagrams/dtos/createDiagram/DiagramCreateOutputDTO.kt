package com.gdeat.service.diagrams.dtos.createDiagram

/**
 * A Graph Creation Output Model.
 *
 * @property Entities an object with entities names and their attributes
 * @property Relations an object with the different relations between the entities
 */
data class DiagramCreateOutputDTO(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
)