package com.gdeat.http.controllers.diagrams.models.deleteDiagram

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO

/**
 * A Graph Creation Output Model.
 *
 * @property diagramCode the code of the graph
 * @property diagramPDF the visualization of the graph
 */
class DeleteDiagramOutputModel(
    val diagramCode: String,
    val diagramPDF: ByteArray
) {

    constructor(diagramCreateOutputDTO: DiagramCreateOutputDTO) : this(
        diagramCode = diagramCreateOutputDTO.diagramCode,
        diagramPDF = diagramCreateOutputDTO.diagramPDF
    )
}