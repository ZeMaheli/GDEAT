package server.externalAI.http.controllers.ai

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import server.externalAI.http.controllers.ai.models.processRequest.ProcessRequestInputModel
import server.externalAI.http.controllers.ai.models.processRequest.ProcessRequestOutputModel
import server.externalAI.http.utils.PathTemplate
import server.externalAI.http.utils.Rels
import server.externalAI.service.ai.AIServiceImpl
import sirenentity.siren.Link
import sirenentity.siren.SirenEntity

@RestController
class AIController(private val aiService: AIServiceImpl) {

    /**
     * Processes the request sent to an AI LLM
     * @param request The ProcessRequestInputModel representing the prompt to be sent to the model
     * @return
     */
    @PostMapping(PathTemplate.PROCESS_REQUEST)
    suspend fun processRequest(@RequestBody request: ProcessRequestInputModel): ResponseEntity<SirenEntity<ProcessRequestOutputModel>>  {
        val response = aiService.processRequest(request.toAIRequest())

        val refreshTokenEntity = SirenEntity(
            `class` = listOf(Rels.PROCESS),
            properties = ProcessRequestOutputModel(response = response.response),
            links = listOf(
                Link(rel = listOf(Rels.PROCESS), href = PathTemplate.processRequest())
            ),
        )

        return refreshTokenEntity.toResponse(HttpStatus.OK)
    }
}