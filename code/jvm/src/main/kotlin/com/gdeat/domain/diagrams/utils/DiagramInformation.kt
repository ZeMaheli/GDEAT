package com.gdeat.domain.diagrams.utils

/**
 * A Diagram Information class.
 *
 * @property Entities an object with entities names and their attributes
 * @property Relations an object with the different relations between the entities
 */
data class DiagramInformation(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
)