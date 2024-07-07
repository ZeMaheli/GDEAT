package com.gdeat.service.diagrams

import com.fasterxml.jackson.databind.ObjectMapper
import com.gdeat.domain.diagrams.Diagram
import com.gdeat.domain.diagrams.utils.DiagramInformation
import com.gdeat.domain.users.User
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
import com.gdeat.service.exceptions.AlreadyExistsException
import com.gdeat.service.exceptions.NotFoundException
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

    override suspend fun createDiagram(diagramCreateInputDTO: DiagramCreateInputDTO): DiagramCreateOutputDTO {
        return aiService.generateEntitiesAndRelations(diagramCreateInputDTO.toAIRequest()).response.toDiagramInfo()
    }

    override fun storeDiagram(token: String, storeDiagramInputDTO: StoreDiagramInputDTO) {
        val user = authenticateUser(token)
        val diagramName = storeDiagramInputDTO.name
        if (diagramsRepository.existsByNameAndUser(diagramName, user)) throw AlreadyExistsException("Diagram with name $diagramName already exists")
        diagramsRepository.save(
            Diagram(
                storeDiagramInputDTO.name,
                user,
                storeDiagramInputDTO.prompt,
                DiagramInformation(storeDiagramInputDTO.Entities, storeDiagramInputDTO.Relations)
            )
        )
    }

    override fun getDiagram(token: String, name: String): GetDiagramOutputDTO {
        val user = authenticateUser(token)
        val diagram = findDiagramByNameAndUser(name, user)
        return GetDiagramOutputDTO(diagram.data)
    }

    override fun deleteDiagram(token: String, name: String): DeleteDiagramOutputDTO {
        val user = authenticateUser(token)
        val diagram = findDiagramByNameAndUser(name, user)
        diagramsRepository.delete(diagram)
        return DeleteDiagramOutputDTO(diagram.name)
    }

    override fun getDiagrams(token: String): GetDiagramsOutputDTO {
        val user = authenticateUser(token)
        val diagrams = diagramsRepository.findAllByUser(user).map { diagram -> diagram.name }
        return GetDiagramsOutputDTO(diagrams)
    }

    /**
     * Finds an user diagram by its name
     *
     * @param name The name of the diagram
     * @param user User information
     *
     * @return The diagram of the user
     * @throws NotFoundException If diagram is not found
     */
    private fun findDiagramByNameAndUser(name: String, user: User): Diagram =
        diagramsRepository.findByNameAndUser(name, user)
            ?: throw NotFoundException("Diagram with name $name not found")

    /**
     * Returns a DiagramCreateOutputDTO representation of the string.
     */
    private fun String.toDiagramInfo(): DiagramCreateOutputDTO {
        return try {
            objectMapper.readValue(this, DiagramCreateOutputDTO::class.java)
        } catch (ex: Exception) {
            throw AIServiceException("Invalid JSON response from LLM: $this")
        }
    }
}