package com.gdeat.service.diagrams.dtos.createDiagram

import com.gdeat.http.controllers.diagrams.models.DiagramCreateOutputModelTests
import org.junit.jupiter.api.Test

class DiagramCreateOutputDTOTests {
    @Test
    fun `DiagramCreateOutputDTO creation is successful`() {
        DiagramCreateOutputDTO(
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))
        )
    }

    companion object{
        val defaultDiagramCreateOutputDTO get() =
            DiagramCreateOutputDTO(
                mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                    Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
                mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))
            )
    }
}