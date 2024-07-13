package com.gdeat.domain.diagrams.utils

import org.junit.jupiter.api.Test

class DiagramInformationTests {
    @Test
    fun `DiagramInformation creation is successful`() {
        DiagramInformation(
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
    }
    companion object {
        val defaultDiagramInformation get() = DiagramInformation(
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
    }
}