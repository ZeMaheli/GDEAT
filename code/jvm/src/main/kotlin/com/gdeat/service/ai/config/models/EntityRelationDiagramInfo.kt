package com.gdeat.service.ai.config.models

data class Entity(
    val attributes: List<String>
)

data class EntityRelationDiagramInfo(
    val Entities: Map<String, Entity>,
    val Relations: Map<String, Map<String, String>>
)