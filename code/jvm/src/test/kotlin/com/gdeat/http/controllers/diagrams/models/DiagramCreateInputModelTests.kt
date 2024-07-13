package com.gdeat.http.controllers.diagrams.models

import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateInputModel
import org.junit.jupiter.api.Test

class DiagramCreateInputModelTests {

    @Test
    fun `DiagramCreateInputModel creation is successful`() {
        DiagramCreateInputModel(
            "test"
        )
    }
    @Test
    fun `DiagramCreateInputModel conversion to DiagramCreateInputDTO is successful`() {
        DiagramCreateInputModel(
            "test"
        ).toGraphCreateDTO()
    }
}