package com.gdeat.http.controllers.diagrams.models.getDiagram

import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO


class GetDiagramOutputModel(
    val diagramCode: String,
    val diagramPDF: ByteArray
) {
    constructor(getDiagramOutputDTO: GetDiagramOutputDTO) : this(
        diagramCode = getDiagramOutputDTO.diagramCode,
        diagramPDF = getDiagramOutputDTO.diagramPDF
    )
}