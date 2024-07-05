package com.gdeat.service.diagrams

import com.fasterxml.jackson.databind.ObjectMapper
import com.gdeat.repository.diagrams.DiagramsRepository
import com.gdeat.repository.users.RevokedAccessTokenRepository
import com.gdeat.repository.users.UsersRepository
import com.gdeat.service.AuthenticationService
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagrams.GetDiagramsOutputDTO
import com.gdeat.service.diagrams.dtos.storeDiagram.StoreDiagramInputDTO
import com.gdeat.service.utils.SecurityConfig
import com.gdeat.utils.JWTProvider
import externalaiservice.ai.AIServiceImpl
import externalaiservice.exceptions.AIServiceException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class DiagramsServiceImpl(
    private val aiService: AIServiceImpl,
    private val objectMapper: ObjectMapper,
    private val diagramsRepository: DiagramsRepository,
    usersRepository: UsersRepository,
    revokedAccessTokenRepository: RevokedAccessTokenRepository,
    jwtProvider: JWTProvider,
    hashingUtils: SecurityConfig
) : DiagramsService,
    AuthenticationService(
        usersRepository,
        revokedAccessTokenRepository,
        jwtProvider,
        hashingUtils
    ) {

    override suspend fun createGraph(diagramCreateInputDTO: DiagramCreateInputDTO): DiagramCreateOutputDTO {
        return aiService.generateEntitiesAndRelations(diagramCreateInputDTO.toAIRequest()).response.fromJsonToEntityRelationDiagramInfo()
    }

    override fun storeDiagram(storeDiagramInputDTO: StoreDiagramInputDTO) {
        TODO("Not yet implemented")
    }

    override fun getDiagram(): GetDiagramOutputDTO {
        TODO("Not yet implemented")
    }

    override fun deleteDiagram(): DeleteDiagramOutputDTO {
        TODO("Not yet implemented")
    }

    override fun getDiagrams(): GetDiagramsOutputDTO {
        TODO("Not yet implemented")
    }

    private fun String.fromJsonToEntityRelationDiagramInfo(): DiagramCreateOutputDTO {
        return try {
            objectMapper.readValue(this, DiagramCreateOutputDTO::class.java)
        } catch (ex: Exception) {
            throw AIServiceException("Invalid JSON response from LLM: $this")
        }
    }
}