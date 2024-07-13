package com.gdeat.http.controllers.diagrams.models

import com.gdeat.http.controllers.diagrams.models.deleteDiagram.DeleteDiagramOutputModel
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import org.junit.jupiter.api.Test

class DeleteDiagramOutputModelTests {
    @Test
    fun `DeleteDiagramOutputModel creation is successful`() {
        DeleteDiagramOutputModel(
            "test"
        )
    }
    @Test
    fun `DeleteDiagramOutputModel creation with DeleteDiagramOutputDTO is successful`() {
        val del = DeleteDiagramOutputDTO("test")
        DeleteDiagramOutputModel(
            del
        )
    }
}