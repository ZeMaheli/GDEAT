package com.gdeat.service.users.dtos.logout

import com.gdeat.config.ServerConfiguration
import com.gdeat.domain.users.RevokedAccessTokenTests
import com.gdeat.service.users.dtos.login.LoginInputDTO
import org.junit.jupiter.api.Test

class LogoutInputDTOTests {

    @Test
    fun `LogoutInputDTO creation is successful`() {
        LogoutInputDTO(
            "acesstoken",
            "password"
        )
    }

    companion object {
        val defaultLogoutInputDTO
            get() = LogoutInputDTO(
                "acesstoken",
                "password"
            )
    }
}