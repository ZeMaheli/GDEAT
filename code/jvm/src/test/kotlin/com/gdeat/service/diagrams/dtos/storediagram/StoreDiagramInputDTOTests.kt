package com.gdeat.service.diagrams.dtos.storediagram

import com.gdeat.service.diagrams.dtos.storeDiagram.StoreDiagramInputDTO
import org.junit.jupiter.api.Test

class StoreDiagramInputDTOTests {
    @Test
    fun `StoreDiagramInputModel creation is successful`() {
        StoreDiagramInputDTO(
            "diagram1",
            "criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                    "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                    "id,nome,idade,telefone",
            mapOf(
                Pair("empregado", listOf("id", "nome", "idade", "telefone")),
                Pair("empresa", listOf("local", "nome", "email", "apoio ao cliente"))
            ),
            mapOf(Pair("relação empregado-empresa", mapOf(Pair("Empregado", "empresa"))))

        )
    }
}