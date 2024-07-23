package com.gdeat.service.diagrams.dtos.getDiagrams

/**
 * A Diagram Information DTO.
 *
 * @property diagrams list of all diagrams names
 * @property totalCount size of diagrams list
 */
data class GetDiagramsOutputDTO(
    val diagrams: List<String>,
    val totalCount: Int
)