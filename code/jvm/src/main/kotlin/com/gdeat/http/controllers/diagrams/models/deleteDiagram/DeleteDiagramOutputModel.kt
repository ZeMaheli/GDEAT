package com.gdeat.http.controllers.diagrams.models.deleteDiagram

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO

/**
 * A Graph Creation Output Model.
 *
 * @property name Name of the deleted diagram
 */
class DeleteDiagramOutputModel(
    val name: String
) {

    constructor(deleteDiagramOutputDTO: DeleteDiagramOutputDTO) : this(
        name = deleteDiagramOutputDTO.name
    )
}