package com.gdeat.service.diagrams.dtos.getDiagram

/**
 * A Diagram Information Output Model.
 *
 * @property Entities an object with entities names and their attributes
 * @property Relations an object with the different relations between the entities
 */
data class GetDiagramOutputDTO(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
)