package com.gdeat.http.controllers.diagrams.models.createDiagram

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO

/**
 * A Graph Creation Output Model.
 *
 * @property entities an object with entities names and their attributes
 * @property relations an object with the different relations between the entities
 */
class DiagramCreateOutputModel(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
) {

    constructor(createDiagramOutputDTO: DiagramCreateOutputDTO) : this(
        Entities = createDiagramOutputDTO.Entities,
        Relations = createDiagramOutputDTO.Relations
    )
}