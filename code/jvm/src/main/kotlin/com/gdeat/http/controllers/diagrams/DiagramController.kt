package com.gdeat.http.controllers.diagrams

import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateInputModel
import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateOutputModel
import com.gdeat.http.controllers.diagrams.models.deleteDiagram.DeleteDiagramOutputModel
import com.gdeat.http.controllers.diagrams.models.getDiagram.GetDiagramOutputModel
import com.gdeat.http.media.siren.Link
import com.gdeat.http.media.siren.SirenEntity
import com.gdeat.http.utils.PathTemplate
import com.gdeat.http.utils.Rels
import com.gdeat.service.diagrams.DiagramsService
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
    @PostMapping(PathTemplate.CREATE_GRAPH)
    suspend fun createGraph(
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
     * Handles the request of a diagram information.
     *
     * @param id The diagram ID obtained from the path variable.
     * @return ResponseEntity with the appropriate status and/or the existing diagram.
     */
    @GetMapping(PathTemplate.GET_GRAPH)
    fun getGraph(
        @PathVariable id: String
    ): ResponseEntity<SirenEntity<GetDiagramOutputModel>> {
        TODO()
    }

    /**
     * Handles editing a diagram.
     *
     * @param id The diagram ID obtained from the path variable.
     * @return ResponseEntity with the appropriate status and/or the created diagram.
     */
    @PutMapping(PathTemplate.EDIT_GRAPH)
    fun editGraph(
        @PathVariable id: String
        //Missing what to edit and/or how
    ): ResponseEntity<SirenEntity<Any/* !!!! */>> {
        TODO()
    }

    /**
     * Handles the deletion of a diagram.
     *
     * @param id The diagram ID obtained from the path variable.
     * @return ResponseEntity with the appropriate status and/or the deleted diagram.
     */
    @DeleteMapping(PathTemplate.DELETE_GRAPH)
    fun deleteGraph(
        @PathVariable id: String
    ): ResponseEntity<SirenEntity<DeleteDiagramOutputModel>> {
        TODO()
    }

}