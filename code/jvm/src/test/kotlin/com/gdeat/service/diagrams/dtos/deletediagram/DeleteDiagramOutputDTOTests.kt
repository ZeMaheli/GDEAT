package com.gdeat.service.diagrams.dtos.deletediagram

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import org.junit.jupiter.api.Test

class DeleteDiagramOutputDTOTests {
    @Test
    fun `DeleteDiagramOutputDTO creation is successful`() {
        DeleteDiagramOutputDTO("diagram1")
    }

    companion object{
        val defaultDeleteDiagramOutputDTO get() =
            DeleteDiagramOutputDTO("diagram1")
    }
}