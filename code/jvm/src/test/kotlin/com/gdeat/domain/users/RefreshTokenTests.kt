package com.gdeat.domain.users

import com.gdeat.domain.exceptions.InvalidTokenException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.sql.Timestamp
import java.time.Instant
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible
import com.gdeat.domain.users.UserTests.Companion.defaultUser

class RefreshTokenTests {

    @Test
    fun `RefreshToken creation is successful`() {
        val tokenHash = "a"
        val expirationDate = Timestamp.from(Instant.now())

        val refreshToken = RefreshToken(
            user = defaultUser,
            tokenHash = tokenHash,
            expirationDate = expirationDate
        )

        val refreshTokenId = RefreshToken::class.declaredMemberProperties
            .first { it.name == "id" }.also { it.isAccessible = true }
            .call(refreshToken) as Int?

        assertNull(refreshTokenId)
        assertEquals(defaultUser, refreshToken.user)
        assertEquals(tokenHash, refreshToken.tokenHash)
        assertEquals(expirationDate, refreshToken.expirationDate)
    }

    @Test
    fun `RefreshToken creation throws InvalidTokenException if the token hash length is invalid`() {
        assertThrows<InvalidTokenException> {
            RefreshToken(
                user = defaultUser,
                tokenHash = "a".repeat(RefreshToken.MAX_TOKEN_HASH_LENGTH + 1),
                expirationDate = Timestamp.from(Instant.now())
            )
        }
    }

    companion object{
        val defaultRefreshToken get() =  RefreshToken(
            user = defaultUser,
            tokenHash = "a",
            expirationDate = Timestamp.from(Instant.now())
        )
    }
}