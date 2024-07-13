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

class RevokedAccessTokenTests {

    @Test
    fun `revoked_access_tokens creation is successful`() {
        val tokenHash = "a".repeat(RevokedAccessToken.TOKEN_HASH_LENGTH)
        val expirationDate = Timestamp.from(Instant.now())

        val revokedAccessToken = RevokedAccessToken(
            user = defaultUser,
            tokenHash = tokenHash,
            expirationDate = expirationDate
        )

        val revokedAccessTokenId = RevokedAccessToken::class.declaredMemberProperties
            .first { it.name == "id" }.also { it.isAccessible = true }
            .call(revokedAccessToken) as Int?

        assertNull(revokedAccessTokenId)
        assertEquals(defaultUser.username, revokedAccessToken.user.username)
        assertEquals(tokenHash, revokedAccessToken.tokenHash)
        assertEquals(expirationDate, revokedAccessToken.expirationDate)
    }

    @Test
    fun `RevokedAccessToken creation throws InvalidRefreshTokenException if the token hash length is invalid`() {
        assertThrows<InvalidTokenException> {
            RevokedAccessToken(
                user = defaultUser,
                tokenHash = "a".repeat(RevokedAccessToken.TOKEN_HASH_LENGTH + 1),
                expirationDate = Timestamp.from(Instant.now())
            )
        }
    }
    companion object{
        val defaultRevokedAccessToken get() = RevokedAccessToken(
            user = defaultUser,
            tokenHash = "a".repeat(RevokedAccessToken.TOKEN_HASH_LENGTH),
            expirationDate = Timestamp.from(Instant.now())
        )
    }
}