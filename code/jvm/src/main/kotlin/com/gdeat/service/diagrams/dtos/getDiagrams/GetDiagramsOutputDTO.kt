package com.gdeat.service.diagrams.dtos.getDiagrams

/**
 * A Diagram Information DTO.
 *
 * @property diagrams list of all diagrams names
 */
data class GetDiagramsOutputDTO(
    val diagrams: List<String>
)