package com.gdeat.http.controllers.diagrams.models

import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateOutputModel
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import org.junit.jupiter.api.Test

class DiagramCreateOutputModelTests {
    @Test
    fun `DiagramCreateOutputModel creation is successful`() {
        DiagramCreateOutputModel(
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
    }
    @Test
    fun `DiagramCreateOutputModel creation with DiagramCreateOutputDTO is successful`() {

        val diagram = DiagramCreateOutputDTO(
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
        DiagramCreateOutputModel(diagram)
    }
    companion object {
        val DiagramCreateOutputModel get()= DiagramCreateOutputDTO(
                mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                    Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
        mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
    }

}