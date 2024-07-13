package pt.isel.daw.battleships.http.controllers.users.models.register

import com.gdeat.http.controllers.users.models.register.RegisterInputModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class RegisterInputModelTests {

    @Test
    fun `RegisterUserInputModel creation is successful`() {
        RegisterInputModel(
            username = "username",
            email = "email",
            password = "password"
        )
    }

    @Test
    fun `RegisterUserInputModel to RegisterUserInputDTO conversion is successful`() {
        val registerUserInputModel = defaultRegisterInputModel

        val registerUserInputDTO = registerUserInputModel.toRegisterInputDTO()

        assertEquals(registerUserInputModel.username, registerUserInputDTO.username)
        assertEquals(registerUserInputModel.email, registerUserInputDTO.email)
        assertEquals(registerUserInputModel.password, registerUserInputDTO.password)
    }

    companion object {
        val defaultRegisterInputModel
            get() = RegisterInputModel(
                username = "username",
                email = "email",
                password = "password"
            )
    }
}
