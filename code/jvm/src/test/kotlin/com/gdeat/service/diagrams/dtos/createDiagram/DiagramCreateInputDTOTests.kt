package com.gdeat.service.diagrams.dtos.createDiagram

import com.gdeat.http.controllers.diagrams.models.createDiagram.DiagramCreateOutputModel
import org.junit.jupiter.api.Test

class DiagramCreateInputDTOTests {
    @Test
    fun `DiagramCreateInputDTO creation is successful`() {
        DiagramCreateInputDTO(
            "criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                "id,nome,idade,telefone"
        )
    }
    @Test
    fun `DiagramCreateInputDTO converting to AiServerRequest creation is successful`() {
        DiagramCreateInputDTO(
            "criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                    "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                    "id,nome,idade,telefone"
        ).toAiServerRequest()
    }

    companion object{
        val defaultDiagramCreateInputDTO get() =
            DiagramCreateInputDTO(
                "criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                        "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                        "id,nome,idade,telefone"
            )
    }
}