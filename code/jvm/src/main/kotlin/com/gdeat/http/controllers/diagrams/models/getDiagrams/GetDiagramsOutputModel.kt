package com.gdeat.http.controllers.diagrams.models.getDiagrams

import com.gdeat.service.diagrams.dtos.getDiagrams.GetDiagramsOutputDTO


class GetDiagramsOutputModel(
    val diagrams: List<String>
) {
    constructor(getDiagramsOutputDTO: GetDiagramsOutputDTO) : this(
        diagrams = getDiagramsOutputDTO.diagrams
    )
}