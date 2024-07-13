package com.gdeat.domain.diagrams

import com.gdeat.domain.diagrams.utils.DiagramInformation
import com.gdeat.domain.exceptions.InvalidDiagramException
import com.gdeat.domain.exceptions.InvalidUserException
import com.gdeat.domain.users.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import com.gdeat.domain.diagrams.utils.DiagramInformationTests.Companion.defaultDiagramInformation
import com.gdeat.domain.users.UserTests.Companion.defaultUser

class DiagramTest {


    @Test
    fun `Diagram creation is successful`() {
        val diagram = Diagram("diagram1",defaultUser,"criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
            "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                    "id,nome,idade,telefone",defaultDiagramInformation)

        assertEquals(diagram.name,"diagram1")
        assertEquals(diagram.user.username, defaultDiagram.user.username)
    }

    @Test
    fun `Diagram creation throws InvalidUserException if username is above max username length `() {
        assertThrows<InvalidUserException> {
            val LongUser = User("TestUserWithMoreThen64Length__________TestUserWithMoreThen64Length",
                "00000000000000000000","TestUser@email.com")
            val diagram = Diagram("diagram1",LongUser,"criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                    "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                    "id,nome,idade,telefone",defaultDiagramInformation)
        }
    }

    @Test
    fun `Diagram creation throws InvalidUserException if username is below least username length`() {
        assertThrows<InvalidUserException> {
            val shortUser = User("eu",
                "00000000000000000000","TestUser@email.com")
            val diagram = Diagram("diagram1",shortUser,"criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                    "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                    "id,nome,idade,telefone",defaultDiagramInformation)
        }
    }

    @Test
    fun `Diagram creation throws InvalidDiagramException if prompt blank`() {
        assertThrows<InvalidDiagramException> {
            Diagram("diagram1",defaultUser,"",defaultDiagramInformation)
        }
    }

    @Test
    fun `Diagram creation throws InvalidDiagramException if Entities isEmpty`() {
        assertThrows<InvalidDiagramException> {
            Diagram("diagram1",defaultUser,"criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                    "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                    "id,nome,idade,telefone", DiagramInformation(mapOf(), mapOf())
            )
        }
    }
    companion object{

        val defaultDiagram get() = Diagram("diagram1",defaultUser,"criar um modelo entidade associação para uma empresa que tem os atributos local, nome,email," +
                "apoio ao cliente e com uma relação com outra entidade cliente que tem os atributos " +
                "id,nome,idade,telefone", defaultDiagramInformation)
    }
}