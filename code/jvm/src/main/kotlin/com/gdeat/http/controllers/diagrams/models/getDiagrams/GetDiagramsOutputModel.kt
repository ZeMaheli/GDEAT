package com.gdeat.http.controllers.diagrams.models.getDiagrams

import com.gdeat.service.diagrams.dtos.getDiagrams.GetDiagramsOutputDTO

/**
 * A Get Diagrams Output Model.
 *
 * @property totalCount the total number of diagrams
 */
class GetDiagramsOutputModel(
    val totalCount: Int
) {
    constructor(getDiagramsOutputDTO: GetDiagramsOutputDTO) : this(
        totalCount = getDiagramsOutputDTO.totalCount
    )
}