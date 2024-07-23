package com.gdeat.service.diagrams

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.gdeat.config.AIServerConfig
import com.gdeat.domain.diagrams.Diagram
import com.gdeat.domain.diagrams.utils.DiagramInformation
import com.gdeat.domain.users.User
import com.gdeat.repository.diagrams.DiagramsRepository
import com.gdeat.repository.tokens.RevokedAccessTokenRepository
import com.gdeat.repository.users.UsersRepository
import com.gdeat.security.JWTProvider
import com.gdeat.config.SecurityConfig
import com.gdeat.service.AuthenticationService
import com.gdeat.service.diagrams.aiserver.AIServerResponse
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagrams.GetDiagramsOutputDTO
import com.gdeat.service.diagrams.dtos.storeDiagram.StoreDiagramInputDTO
import com.gdeat.service.exceptions.AIServerException
import com.gdeat.service.exceptions.AlreadyExistsException
import com.gdeat.service.exceptions.NotFoundException
import com.google.gson.Gson
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientRequestException
import sirenentity.siren.SirenEntity

@Service
@Transactional(rollbackFor = [Exception::class])
class DiagramsServiceImpl(
    private val aiServerConfig: AIServerConfig,
    private val gson: Gson,
    private val webClient: WebClient,
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
        try {
            val sirenEntityRes = webClient
                .post()
                .uri(aiServerConfig.endpoints.process)
                .bodyValue(diagramCreateInputDTO.toAiServerRequest())
                .retrieve()
                .bodyToMono(SirenEntity::class.java)
                .awaitFirst()
            // Ensure res is not null and properties is a LinkedHashMap
            val properties = sirenEntityRes?.properties as? LinkedHashMap<*, *> ?: throw AIServerException("Properties not found in response or not of expected type")

            // Extract response JSON string
            val responseJson = properties["response"] as? String ?: throw AIServerException("Response JSON not found in properties")

            // Deserialize response JSON string into SomeClass using Jackson ObjectMapper
            val someClass = jacksonObjectMapper().readValue(responseJson, DiagramCreateOutputDTO::class.java)

            // Create DiagramCreateOutputDTO from SomeClass
            val diagramOutputDTO = DiagramCreateOutputDTO(
                Entities = someClass.Entities,
                Relations = someClass.Relations
            )

            return diagramOutputDTO
        } catch (ex: WebClientRequestException) {
            throw AIServerException("Unable to communicate with AI server. Possibly down")
        } catch (ex: Exception) {
            println(ex)
            throw AIServerException("Exception")
        }
    }


    override fun storeDiagram(token: String, storeDiagramInputDTO: StoreDiagramInputDTO) {
        val user = authenticateUser(token)
        val diagramName = storeDiagramInputDTO.name
        if (diagramsRepository.existsByNameAndUser(
                diagramName,
                user
            )
        ) throw AlreadyExistsException("Diagram with name $diagramName already exists")
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
        return GetDiagramsOutputDTO(diagrams, diagrams.size)
    }

    /**
     * Finds a user diagram by its name
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

/*    *//**
     * Returns a DiagramCreateOutputDTO representation of the string.
     *//*
    private fun String.toDiagramInfo(): DiagramCreateOutputDTO {
        return try {
            gson.fromJson(this, AIServerResponse::class.java).response
        } catch (ex: Exception) {
            throw AIServerException("Invalid JSON response from LLM: $this")
        }
    }*/
}