package com.gdeat.service.users.dtos.register

import org.junit.jupiter.api.Test

class RegisterOutputDTOTests {

    @Test
    fun `RegisterUserOutputDTO creation is successful`() {
        RegisterOutputDTO(
            username = "username",
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )
    }

    companion object {
        val defaultRegisterOutputDTO
            get() = RegisterOutputDTO(
                username = "username",
                accessToken = "accessToken",
                refreshToken = "refreshToken"
            )
    }
}
