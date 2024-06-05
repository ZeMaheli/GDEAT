package com.gdeat.service.diagrams

import com.gdeat.service.ai.AIServiceImpl
import com.gdeat.service.ai.config.models.AIRequest
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class DiagramsServiceImpl(private val aiService: AIServiceImpl) : DiagramsService {
    override suspend fun createGraph(diagramCreateInputDTO: DiagramCreateInputDTO): DiagramCreateOutputDTO {
        val diagramInfo = aiService.generateEntitiesAndRelations(AIRequest.toAIRequest(diagramCreateInputDTO))

    }

    override fun getGraph(): GetDiagramOutputDTO {
        TODO("Not yet implemented")
    }

    override fun editGraph() {
        TODO("Not yet implemented")
    }

    override fun deleteGraph(): DeleteDiagramOutputDTO {
        TODO("Not yet implemented")
    }
}