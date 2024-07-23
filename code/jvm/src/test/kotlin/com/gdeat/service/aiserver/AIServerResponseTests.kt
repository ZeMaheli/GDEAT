package com.gdeat.service.aiserver

import com.gdeat.http.controllers.diagrams.models.DiagramCreateOutputModelTests
import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateOutputModel
import com.gdeat.service.diagrams.aiserver.AIServerRequest
import com.gdeat.service.diagrams.aiserver.AIServerResponse
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTOTests
import org.junit.jupiter.api.Test

class AIServerResponseTests {
    @Test
    fun `AIServerResponse creation is successful`() {
        AIServerResponse(
            DiagramCreateOutputDTOTests.defaultDiagramCreateOutputDTO.toString()
        )
    }
}