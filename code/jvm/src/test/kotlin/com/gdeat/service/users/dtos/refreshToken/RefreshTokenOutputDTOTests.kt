package com.gdeat.service.users.dtos.refreshToken

import com.gdeat.service.users.dtos.token.RefreshTokenOutputDTO
import org.junit.jupiter.api.Test

class RefreshTokenOutputDTOTests {

    @Test
    fun `RefreshTokenOutputDTO creation is successful`() {
        RefreshTokenOutputDTO(
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )
    }

    companion object {
        val defaultRefreshTokenOutputDTO
            get() = RefreshTokenOutputDTO(
                accessToken = "accessToken",
                refreshToken = "refreshToken"
            )
    }
}
