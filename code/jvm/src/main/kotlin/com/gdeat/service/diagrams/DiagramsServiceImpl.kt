package com.gdeat.service.diagrams

import com.fasterxml.jackson.databind.ObjectMapper
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import externalaiservice.ai.AIServiceImpl
import externalaiservice.exceptions.AIServiceException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class DiagramsServiceImpl(
    private val aiService: AIServiceImpl,
    private val objectMapper: ObjectMapper
) : DiagramsService {

    override suspend fun createGraph(diagramCreateInputDTO: DiagramCreateInputDTO): DiagramCreateOutputDTO {
        return aiService.generateEntitiesAndRelations(diagramCreateInputDTO.toAIRequest()).response.fromJsonToEntityRelationDiagramInfo()
    }

    private fun String.fromJsonToEntityRelationDiagramInfo(): DiagramCreateOutputDTO {
        return try {
            objectMapper.readValue(this, DiagramCreateOutputDTO::class.java)
        } catch (ex: Exception) {
            throw AIServiceException("Invalid JSON response from LLM: $this")
        }
    }

    override fun getGraph(): GetDiagramOutputDTO {
        TODO("Not yet implemented")
    }

    override fun deleteGraph(): DeleteDiagramOutputDTO {
        TODO("Not yet implemented")
    }
}