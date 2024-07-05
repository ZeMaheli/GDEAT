package com.gdeat.http.controllers.diagrams

import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateInputModel
import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateOutputModel
import com.gdeat.http.controllers.diagrams.models.deleteDiagram.DeleteDiagramOutputModel
import com.gdeat.http.controllers.diagrams.models.getDiagram.GetDiagramOutputModel
import com.gdeat.http.controllers.diagrams.models.storeDiagram.StoreDiagramInputModel
import com.gdeat.http.media.siren.Link
import com.gdeat.http.media.siren.SirenEntity
import com.gdeat.http.utils.PathTemplate
import com.gdeat.http.utils.Rels
import com.gdeat.service.diagrams.DiagramsService
import com.gdeat.utils.JWTProvider.Companion.ACCESS_TOKEN_ATTRIBUTE
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DiagramController(private val diagramServices: DiagramsService) {

    /**
     * Handles the creation of a diagram.
     *
     * @param promptData The DiagramCreateInputModel representing the prompt to be sent to the llm.
     * @return ResponseEntity with the appropriate status and/or the created diagram.
     */
    @PostMapping(PathTemplate.CREATE_DIAGRAM)
    suspend fun createDiagram(
        @RequestBody promptData: DiagramCreateInputModel
    ): ResponseEntity<SirenEntity<DiagramCreateOutputModel>> {
        val graphInfoDTO = diagramServices.createGraph(promptData.toGraphCreateDTO())

        val createDiagramEntity = SirenEntity(
            `class` = listOf(Rels.CREATE_DIAGRAM),
            properties = DiagramCreateOutputModel(graphInfoDTO),
            links = listOf(
                Link(rel = listOf(Rels.CREATE_DIAGRAM), href = PathTemplate.diagramCreate())
            ),
        )

        return createDiagramEntity.toResponse(HttpStatus.CREATED)
    }

    /**
     * Handles the request to store a diagram.
     * @param token the access token of the user
     * @param id The diagram ID obtained from the path variable.
     * @return ResponseEntity with the appropriate status and/or the existing diagram.
     */
    @PostMapping(PathTemplate.STORE_DIAGRAM)
    fun saveDiagram(
        @RequestAttribute(ACCESS_TOKEN_ATTRIBUTE) token: String,
        @RequestBody diagramData: StoreDiagramInputModel
    ): ResponseEntity<SirenEntity<Unit>> {
        TODO()
    }

    /**
     * Handles the request of a diagram information.
     *
     * @param id The diagram ID obtained from the path variable.
     * @return ResponseEntity with the appropriate status and/or the existing diagram.
     */
    @GetMapping(PathTemplate.GET_DIAGRAM)
    fun getDiagram(
        @RequestAttribute(ACCESS_TOKEN_ATTRIBUTE) token: String,
        @PathVariable id: String
    ): ResponseEntity<SirenEntity<GetDiagramOutputModel>> {
        TODO()
    }

    /**
     * Handles the deletion of a diagram.
     *
     * @param token the access token of the user
     * @param id The diagram ID obtained from the path variable.
     * @return ResponseEntity with the appropriate status and/or the deleted diagram.
     */
    @DeleteMapping(PathTemplate.DELETE_DIAGRAMS)
    fun deleteDiagram(
        @RequestAttribute(ACCESS_TOKEN_ATTRIBUTE) token: String,
        @PathVariable id: String
    ): ResponseEntity<SirenEntity<DeleteDiagramOutputModel>> {
        TODO()
    }

    /**
     * Handles the request of all user diagrams information.
     * @param token the access token of the user
     * @return ResponseEntity with the appropriate status and/or the existing diagram.
     */
    @GetMapping(PathTemplate.GET_DIAGRAMS)
    fun getDiagrams(
        @RequestAttribute(ACCESS_TOKEN_ATTRIBUTE) token: String,
    ): ResponseEntity<SirenEntity<GetDiagramOutputModel>> {
        TODO()
    }

}