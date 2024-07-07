package com.gdeat.http.controllers.diagrams

import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateInputModel
import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateOutputModel
import com.gdeat.http.controllers.diagrams.models.deleteDiagram.DeleteDiagramOutputModel
import com.gdeat.http.controllers.diagrams.models.getDiagram.GetDiagramOutputModel
import com.gdeat.http.controllers.diagrams.models.getDiagrams.GetDiagramsOutputModel
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
        val graphInfoDTO = diagramServices.createDiagram(promptData.toGraphCreateDTO())

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
     * @param diagramData The StoreDiagramInputModel representing the diagram information to be stored.
     * @return ResponseEntity with the appropriate status and/or the existing diagram.
     */
    @PostMapping(PathTemplate.STORE_DIAGRAM)
    fun saveDiagram(
        @RequestAttribute(ACCESS_TOKEN_ATTRIBUTE) token: String,
        @RequestBody diagramData: StoreDiagramInputModel
    ): ResponseEntity<SirenEntity<Unit>> {
        diagramServices.storeDiagram(token, diagramData.toDiagramStoreDTO())

        val storeDiagramEntity = SirenEntity(
            `class` = listOf(Rels.STORE_DIAGRAM),
            properties = Unit,
            links = listOf(
                Link(rel = listOf(Rels.STORE_DIAGRAM), href = PathTemplate.diagramsStore())
            ),
        )

        return storeDiagramEntity.toResponse(HttpStatus.OK)
    }

    /**
     * Handles the request of a diagram information.
     *
     * @param token the access token of the user
     * @param name The diagram name obtained from the path variable.
     * @return ResponseEntity with the appropriate status and/or the existing diagram.
     */
    @GetMapping(PathTemplate.GET_DIAGRAM)
    fun getDiagram(
        @RequestAttribute(ACCESS_TOKEN_ATTRIBUTE) token: String,
        @PathVariable name: String
    ): ResponseEntity<SirenEntity<GetDiagramOutputModel>> {
        val diagramInfo = diagramServices.getDiagram(token, name)

        val getDiagramEntity = SirenEntity(
            `class` = listOf(Rels.GET_DIAGRAM),
            properties = GetDiagramOutputModel(diagramInfo),
            links = listOf(
                Link(rel = listOf(Rels.GET_DIAGRAM), href = PathTemplate.diagramsGet())
            ),
        )

        return getDiagramEntity.toResponse(HttpStatus.OK)
    }

    /**
     * Handles the deletion of a diagram.
     *
     * @param token the access token of the user
     * @param name The diagram name obtained from the path variable.
     * @return ResponseEntity with the appropriate status and/or the deleted diagram.
     */
    @DeleteMapping(PathTemplate.DELETE_DIAGRAMS)
    fun deleteDiagram(
        @RequestAttribute(ACCESS_TOKEN_ATTRIBUTE) token: String,
        @PathVariable name: String
    ): ResponseEntity<SirenEntity<DeleteDiagramOutputModel>> {
        val deletedDiagramInfo = diagramServices.deleteDiagram(token, name)

        val deleteDiagramEntity = SirenEntity(
            `class` = listOf(Rels.DELETE_DIAGRAM),
            properties = DeleteDiagramOutputModel(deletedDiagramInfo),
            links = listOf(
                Link(rel = listOf(Rels.DELETE_DIAGRAM), href = PathTemplate.diagramDelete())
            ),
        )

        return deleteDiagramEntity.toResponse(HttpStatus.OK)
    }

    /**
     * Handles the request of all user diagrams information.
     * @param token the access token of the user
     * @return ResponseEntity with the appropriate status and/or the existing diagram.
     */
    @GetMapping(PathTemplate.GET_DIAGRAMS)
    fun getDiagrams(
        @RequestAttribute(ACCESS_TOKEN_ATTRIBUTE) token: String,
    ): ResponseEntity<SirenEntity<GetDiagramsOutputModel>> {
        val diagrams = diagramServices.getDiagrams(token)

        val getDiagramEntity = SirenEntity(
            `class` = listOf(Rels.GET_DIAGRAMS),
            properties = GetDiagramsOutputModel(diagrams),
            links = listOf(
                Link(rel = listOf(Rels.GET_DIAGRAMS), href = PathTemplate.diagramsGet())
            ),
        )

        return getDiagramEntity.toResponse(HttpStatus.OK)
    }

}