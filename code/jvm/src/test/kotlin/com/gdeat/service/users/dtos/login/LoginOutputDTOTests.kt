package com.gdeat.service.users.dtos.login

import org.junit.jupiter.api.Test

class LoginOutputDTOTests {

    @Test
    fun `LoginUserOutputDTO creation is successful`() {
        LoginOutputDTO(
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )
    }

    companion object {
        val defaultLoginOutputDTO
            get() = LoginOutputDTO(
                accessToken = "accessToken",
                refreshToken = "refreshToken"
            )
    }
}
