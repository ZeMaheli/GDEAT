package com.gdeat.http.controllers.users.models.register

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.gdeat.service.users.dtos.register.RegisterOutputDTOTests.Companion.defaultRegisterOutputDTO

class RegisterOutputModelTests {

    @Test
    fun `RegisterUserOutputModel creation is successful`() {
        RegisterOutputModel(
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )
    }

    @Test
    fun `RegisterUserOutputModel from RegisterUserOutputDTO conversion is successful`() {
        val registerUserOutputDTO = defaultRegisterOutputDTO

        val registerOutputModel = RegisterOutputModel(registerUserOutputDTO)

        assertEquals(registerUserOutputDTO.accessToken, registerOutputModel.accessToken)
        assertEquals(registerUserOutputDTO.refreshToken, registerOutputModel.refreshToken)
    }

    companion object {
        val defaultRegisterOutputModel
            get() = RegisterOutputModel(
                accessToken = "accessToken",
                refreshToken = "refreshToken"
            )
    }
}
