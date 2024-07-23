package com.gdeat.utils

import com.gdeat.config.ServerConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ServerConfigurationTests {

    @Test
    fun `ServerConfiguration creation is successful`() {
        val accessTokenSecret = "accessTokenSecret"
        val refreshTokenSecret = "refreshTokenSecret"
        val maxRefreshTokens = 10

        val serverConfiguration = ServerConfiguration(
            accessTokenSecret = accessTokenSecret,
            refreshTokenSecret = refreshTokenSecret,
            maxRefreshTokens = maxRefreshTokens
        )

        assertEquals(accessTokenSecret, serverConfiguration.accessTokenSecret)
        assertEquals(refreshTokenSecret, serverConfiguration.refreshTokenSecret)
        assertEquals(maxRefreshTokens, serverConfiguration.maxRefreshTokens)
    }
}
