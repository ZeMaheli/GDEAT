package com.gdeat.http.controllers.diagrams.models

import com.gdeat.http.controllers.diagrams.models.getDiagram.GetDiagramOutputModel
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import org.junit.jupiter.api.Test

class GetDiagramOutputModelTests {
    @Test
    fun `GetDiagramOutputModel creation is successful`() {
        GetDiagramOutputModel(
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
    }
    @Test
    fun `GetDiagramOutputModel creation with DiagramCreateOutputDTO is successful`() {

        val diagram = GetDiagramOutputDTO(
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
        GetDiagramOutputModel(diagram)
    }

}