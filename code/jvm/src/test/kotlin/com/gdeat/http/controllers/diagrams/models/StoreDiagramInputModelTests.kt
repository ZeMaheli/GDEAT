package com.gdeat.http.controllers.diagrams.models

import com.gdeat.http.controllers.diagrams.models.storeDiagram.StoreDiagramInputModel
import org.junit.jupiter.api.Test

class StoreDiagramInputModelTests {
    @Test
    fun `StoreDiagramInputModel creation is successful`() {
        StoreDiagramInputModel(
            "diagram1",
            "criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                    "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                    "id,nome,idade,telefone",
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
    }
    @Test
    fun `StoreDiagramInputDTO creation with StoreDiagramInputModel is successful`() {

        val diagram = StoreDiagramInputModel(
            "diagram1",
            "criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                    "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                    "id,nome,idade,telefone",
            mapOf(Pair("empregado", listOf("id","nome","idade","telefone")),
                Pair("empresa", listOf("local","nome","email","apoio ao cliente"))),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado","empresa")) ))

        )
        diagram.toDiagramStoreDTO()
    }
}