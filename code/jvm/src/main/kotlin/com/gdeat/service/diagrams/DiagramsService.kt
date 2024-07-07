package com.gdeat.service.diagrams

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagrams.GetDiagramsOutputDTO
import com.gdeat.service.diagrams.dtos.storeDiagram.StoreDiagramInputDTO

interface DiagramsService {
    /**
     * Creates a new diagram
     *
     * @param diagramCreateInputDTO the DTO with the data to create a new diagram
     *
     * @return The diagram information as a DTO
     */
    suspend fun createDiagram(diagramCreateInputDTO: DiagramCreateInputDTO): DiagramCreateOutputDTO

    /**
     * Stores a diagram
     *
     * @param token User access token
     * @param storeDiagramInputDTO the DTO with the data of the diagram to store
     */
    fun storeDiagram(token: String, storeDiagramInputDTO: StoreDiagramInputDTO)

    /**
     * Gets a diagram information
     *
     * @param token User access token
     * @param name the name of the diagram
     */
    fun getDiagram(token: String, name: String): GetDiagramOutputDTO

    /**
     * Deletes a diagram
     *
     * @param token User access token
     * @param name the name of the diagram
     */
    fun deleteDiagram(token: String, name: String): DeleteDiagramOutputDTO

    /**
     * Get all user diagrams
     *
     * @param token User access token
     */
    fun getDiagrams(token: String): GetDiagramsOutputDTO
}