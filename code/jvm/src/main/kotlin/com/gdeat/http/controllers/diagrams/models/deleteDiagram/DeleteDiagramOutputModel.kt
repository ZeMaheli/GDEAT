package com.gdeat.http.controllers.diagrams.models.deleteDiagram

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO

/**
 * A Graph Creation Output Model.
 *
 * @property entities an object with entities names and their attributes
 * @property relations an object with the different relations between the entities
 */
class DeleteDiagramOutputModel(
    val Entities: Map<String, List<String>>,
    val Relations: Map<String, Map<String, String>>
) {

    constructor(diagramCreateOutputDTO: DiagramCreateOutputDTO) : this(
        Entities = diagramCreateOutputDTO.Entities,
        Relations = diagramCreateOutputDTO.Relations
    )
}