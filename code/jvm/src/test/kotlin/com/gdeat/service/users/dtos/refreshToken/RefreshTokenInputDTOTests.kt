package com.gdeat.service.users.dtos.refreshToken

import com.gdeat.service.users.dtos.token.RefreshTokenInputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenOutputDTO
import org.junit.jupiter.api.Test

class RefreshTokenInputDTOTests {
    @Test
    fun `RefreshTokenInputDTO creation is successful`() {
        RefreshTokenInputDTO(
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )
    }

    companion object {
        val defaultRefreshTokenInputDTO
            get() = RefreshTokenInputDTO(
                accessToken = "accessToken",
                refreshToken = "refreshToken"
            )
    }
}