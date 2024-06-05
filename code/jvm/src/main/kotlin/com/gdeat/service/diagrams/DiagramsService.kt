package com.gdeat.service.diagrams

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO

interface DiagramsService {
    suspend fun createGraph(diagramCreateInputDTO: DiagramCreateInputDTO): DiagramCreateOutputDTO

    fun getGraph(): GetDiagramOutputDTO

    fun editGraph()

    fun deleteGraph(): DeleteDiagramOutputDTO
}