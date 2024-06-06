package com.gdeat.http.controllers.diagrams.models.createDiagram

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO

/**
 * A Graph Creation Output Model.
 *
 * @property diagramCode the code of the graph
 * @property diagramPDF the visualization of the graph
 */
class DiagramCreateOutputModel(
    val diagramCode: String,
    val diagramPDF: ByteArray
) {

    constructor(createDiagramOutputDTO: DiagramCreateOutputDTO) : this(
        diagramCode = createDiagramOutputDTO.diagramCode,
        diagramPDF = createDiagramOutputDTO.diagramPDF
    )
}