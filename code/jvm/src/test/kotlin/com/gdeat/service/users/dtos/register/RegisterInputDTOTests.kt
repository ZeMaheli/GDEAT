package com.gdeat.service.users.dtos.register

import com.gdeat.service.users.dtos.register.RegisterInputDTO
import org.junit.jupiter.api.Test

class RegisterInputDTOTests {

    @Test
    fun `RegisterUserInputDTO creation is successful`() {
        RegisterInputDTO(
            username = "username",
            email = "email",
            password = "password"
        )
    }

    companion object {
        val defaultRegisterInputDTO
            get() = RegisterInputDTO(
                username = "username",
                email = "email",
                password = "password"
            )
    }
}
