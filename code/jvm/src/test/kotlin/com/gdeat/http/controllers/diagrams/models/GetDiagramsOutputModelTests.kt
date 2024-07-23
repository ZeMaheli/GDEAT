package com.gdeat.http.controllers.diagrams.models

import com.gdeat.http.controllers.diagrams.models.getDiagrams.GetDiagramsOutputModel
import com.gdeat.service.diagrams.dtos.getDiagrams.GetDiagramsOutputDTO
import org.junit.jupiter.api.Test

class GetDiagramsOutputModelTests {
    @Test
    fun `GetDiagramsOutputModel creation is successful`() {
        GetDiagramsOutputModel(
            3
        )
    }

    @Test
    fun `GetDiagramsOutputModel creation with DiagramCreateOutputDTO is successful`() {

        val diagram = GetDiagramsOutputDTO(
            listOf("diagram1", "diagram2", "diagram3"),
            3
        )
        GetDiagramsOutputModel(diagram)
    }
}