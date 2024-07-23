package com.gdeat.service.aiserver

import com.gdeat.domain.diagrams.Diagram
import com.gdeat.domain.diagrams.utils.DiagramInformationTests.Companion.defaultDiagramInformation
import com.gdeat.domain.users.UserTests.Companion.defaultUser
import com.gdeat.service.diagrams.aiserver.AIServerRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AIServerRequestTests {
    @Test
    fun `AIServerRequest creation is successful`() {
        AIServerRequest(
            "criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                "id,nome,idade,telefone"
        )
    }
}