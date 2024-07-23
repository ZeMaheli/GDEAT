package com.gdeat.http.controllers.diagrams.models.getDiagram

import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO

class GetDiagramOutputModel(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
) {
    constructor(getDiagramOutputDTO: GetDiagramOutputDTO) : this(
        Entities = getDiagramOutputDTO.Entities,
        Relations = getDiagramOutputDTO.Relations
    )
}