package com.gdeat.repository.refreshTokens

import com.gdeat.domain.users.RefreshToken
import com.gdeat.domain.users.User
import com.gdeat.repository.tokens.RefreshTokensRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import com.gdeat.testUtils.DatabaseTest
import org.springframework.boot.test.context.SpringBootTest
import java.sql.Timestamp
import java.time.Instant


@SpringBootTest
class RefreshTokensRepositoryTests : DatabaseTest() {
    @Autowired
    lateinit var refreshTokensRepository: RefreshTokensRepository

    @Test
    fun `findByUserAndTokenHash returns the refresh token given the user and the token hash`() {
        val user = entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        val tokenHash = "a".repeat(RefreshToken.MAX_TOKEN_HASH_LENGTH)

        val refreshToken = entityManager.persist(
            RefreshToken(
                user = user,
                tokenHash = tokenHash,
                expirationDate = Timestamp.from(Instant.now())
            )
        )

        val token = refreshTokensRepository.findByUserAndTokenHash(user, tokenHash)

        assertNotNull(token)
        assertEquals(refreshToken, token)
    }

    @Test
    fun `findByUserAndTokenHash returns null if the user does not have a refresh token with the given token hash`() {
        val user = entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        val token = refreshTokensRepository.findByUserAndTokenHash(user, "tokenHash")

        assertNull(token)
    }

    @Test
    fun `countByUser returns the number of refresh tokens for the given user`() {
        val user = entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        repeat(3) {
            entityManager.persist(
                RefreshToken(
                    user = user,
                    tokenHash = "$it".repeat(RefreshToken.MAX_TOKEN_HASH_LENGTH),
                    expirationDate = Timestamp.from(Instant.now())
                )
            )
        }

        val count = refreshTokensRepository.countByUser(user)

        assertEquals(3, count)
    }

    @Test
    fun `countByUser returns 0 if the given user has no refresh tokens`() {
        val user = entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        val count = refreshTokensRepository.countByUser(user)

        assertEquals(0, count)
    }

    @Test
    fun `getRefreshTokensOfUser returns the refresh tokens of the given user ordered by expiration date`() {
        val user = entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        val tokens = List(3) {
            entityManager.persist(
                RefreshToken(
                    user = user,
                    tokenHash = "$it".repeat(RefreshToken.MAX_TOKEN_HASH_LENGTH),
                    expirationDate = Timestamp.from(Instant.now())
                )
            )
        }

        val foundTokens = refreshTokensRepository.getRefreshTokensOfUser(
            user = user
        )

        assertEquals(3, foundTokens.size)
        assertEquals(tokens, foundTokens)
    }

    @Test
    fun `getRefreshTokensOfUser returns empty if there are no refresh tokens of the given user`() {
        val user = entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        val foundTokens = refreshTokensRepository.getRefreshTokensOfUser(
            user = user
        )

        assertEquals(0, foundTokens.size)
        assertEquals(emptyList<RefreshToken>(), foundTokens)
    }
}
