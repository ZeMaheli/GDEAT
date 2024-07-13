package com.gdeat.service.diagrams.dtos.getdiagram

import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import org.junit.jupiter.api.Test

class GetDiagramOutputDTOTests {
    @Test
    fun `GetDiagramOutputDTO creation is successful`() {
        GetDiagramOutputDTO(
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))
        )
    }

    companion object{
        val defaultgetdiagramoutputDTO get() =
            GetDiagramOutputDTO(
                mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                    Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
                mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))
            )
    }
}