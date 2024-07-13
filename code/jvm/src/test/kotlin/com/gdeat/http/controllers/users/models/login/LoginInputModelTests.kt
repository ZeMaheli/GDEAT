package com.gdeat.http.controllers.users.models.login

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LoginInputModelTests {

    @Test
    fun `LoginUserInputModel creation is successful`() {
        LoginInputModel(
            username = "username",
            password = "password"
        )
    }

    @Test
    fun `LoginUserInputModel to LoginUserInputDTO conversion is successful`() {
        val loginUserInputModel = defaultLoginInputModel

        val loginUserInputDTO = loginUserInputModel.toLoginInputDTO()

        assertEquals(loginUserInputModel.username, loginUserInputDTO.username)
    }

    companion object {
        val defaultLoginInputModel
            get() = LoginInputModel(
                username = "username",
                password = "password"
            )
    }
}
