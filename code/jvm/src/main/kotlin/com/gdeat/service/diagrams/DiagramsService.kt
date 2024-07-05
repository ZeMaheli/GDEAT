package com.gdeat.service.diagrams

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagrams.GetDiagramsOutputDTO
import com.gdeat.service.diagrams.dtos.storeDiagram.StoreDiagramInputDTO

interface DiagramsService {
    suspend fun createGraph(diagramCreateInputDTO: DiagramCreateInputDTO): DiagramCreateOutputDTO

    fun storeDiagram(storeDiagramInputDTO: StoreDiagramInputDTO)

    fun getDiagram(): GetDiagramOutputDTO

    fun deleteDiagram(): DeleteDiagramOutputDTO

    fun getDiagrams(): GetDiagramsOutputDTO
}