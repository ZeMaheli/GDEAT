package com.gdeat.service.users.dtos.login

import com.gdeat.service.users.dtos.login.LoginInputDTO
import org.junit.jupiter.api.Test

class LoginInputDTOTests {

    @Test
    fun `LoginUserInputDTO creation is successful`() {
        LoginInputDTO(
            username = "username",
            password = "password"
        )
    }

    companion object {
        val defaultLoginInputDTO
            get() = LoginInputDTO(
                username = "username",
                password = "password"
            )
    }
}
