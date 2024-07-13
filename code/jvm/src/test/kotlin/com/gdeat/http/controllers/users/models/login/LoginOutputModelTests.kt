package com.gdeat.http.controllers.users.models.login

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.gdeat.service.users.dtos.login.LoginOutputDTOTests.Companion.defaultLoginOutputDTO


class LoginOutputModelTests {

    @Test
    fun `LoginUserOutputModel creation is successful`() {
        LoginOutputModel(
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )
    }

    @Test
    fun `LoginUserOutputModel from LoginUserOutputDTO conversion is successful`() {
        val loginUserOutputDTO = defaultLoginOutputDTO

        val loginOutputModel = LoginOutputModel(loginUserOutputDTO)

        assertEquals(loginUserOutputDTO.accessToken, loginOutputModel.accessToken)
        assertEquals(loginUserOutputDTO.refreshToken, loginOutputModel.refreshToken)
    }

    companion object {
        val defaultLoginOutputModel
            get() = LoginOutputModel(
                accessToken = "accessToken",
                refreshToken = "refreshToken"
            )
    }
}
