package com.gdeat.service.diagrams.dtos.getdiagrams

import com.gdeat.service.diagrams.dtos.getDiagrams.GetDiagramsOutputDTO
import org.junit.jupiter.api.Test

class getGetDiagramsTests {

    @Test
    fun `GetDiagramsOutputDTO creation is successful`() {
        GetDiagramsOutputDTO(
            listOf("dia1", "dia2", "dia3"),
            3
        )
    }

    companion object {
        val defaultgetdiagramsoutputDTO
            get() =
                GetDiagramsOutputDTO(
                    listOf("dia1", "dia2", "dia3"),
                    3
                )
    }
}