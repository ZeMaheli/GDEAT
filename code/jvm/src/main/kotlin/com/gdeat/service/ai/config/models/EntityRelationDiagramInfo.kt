package com.gdeat.service.ai.config.models

data class EntityRelationDiagramInfo(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
)
