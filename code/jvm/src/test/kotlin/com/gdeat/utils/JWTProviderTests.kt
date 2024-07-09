package com.gdeat.utils

import com.gdeat.config.ServerConfiguration
import com.gdeat.security.JWTProvider
import com.gdeat.security.JWTProvider.JwtPayload
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.SignatureException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.sql.Timestamp
import java.time.Instant
import javax.crypto.SecretKey
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

@SpringBootTest
class JWTProviderTests {

    @Autowired
    lateinit var jwtProvider: JWTProvider

    val jwtProviderAccessTokenKey by lazy {
        jwtProvider::class.declaredMemberProperties
            .first { it.name == "accessTokenKey" }.also { it.isAccessible = true }
            .call(jwtProvider) as SecretKey
    }

    val jwtProviderRefreshTokenKey by lazy {
        jwtProvider::class.declaredMemberProperties
            .first { it.name == "refreshTokenKey" }.also { it.isAccessible = true }
            .call(jwtProvider) as SecretKey
    }

    /**
     * Gets the payload of a JWT token.
     *
     * @param token the token to get the payload from
     *
     * @return the claims of the token
     */
    private fun getTokenClaims(token: String, tokenKey: SecretKey) =
        JwtPayload(
            claims = Jwts.parser()
                .verifyWith(tokenKey)
                .build()
                .parseSignedClaims(token).payload
        )

    @Test
    fun `JwtProvider creation is successful`() {
        JWTProvider(
            serverConfig = ServerConfiguration(
                accessTokenSecret = "accessTokenSecret",
                refreshTokenSecret = "refreshTokenSecret",
                maxRefreshTokens = 3
            )
        )
    }

    @Test
    fun `createAccessToken returns a valid jwt with the right payload`() {
        val username = "TestUsername1"
        val payload = JwtPayload.fromData(username)
        val accessToken = jwtProvider.createAccessToken(payload)
        val claims = getTokenClaims(accessToken, jwtProviderAccessTokenKey).claims

        assertEquals(payload.username, claims.subject)
    }

    @Test
    fun `createRefreshToken returns a valid jwt with the right payload`() {
        val username = "TestUsername1"
        val payload = JwtPayload.fromData(username)
        val refreshToken = jwtProvider.createRefreshToken(payload)
        val claims = getTokenClaims(refreshToken.token, jwtProviderRefreshTokenKey).claims

        assertEquals(payload.username, claims.subject)
    }

    @Test
    fun `validateAccessToken returns the right payload if token is valid`() {
        val payload = JwtPayload.fromData(username = "UsernameTest2")

        val token = jwtProvider.createAccessToken(payload)

        val validatedPayload = jwtProvider.validateAccessToken(token)

        assertNotNull(validatedPayload)
        assertEquals(payload.username, validatedPayload?.username)
    }

    @Test
    fun `validateAccessToken returns null if token is not valid`() {
        val token = "InvalidToken"

        val validatedPayload = jwtProvider.validateAccessToken(token)

        assertNull(validatedPayload)
    }

    @Test
    fun `validateRefreshToken returns the right payload if token is valid`() {
        val payload = JwtPayload.fromData(username = "UsernameTest3")

        val (token) = jwtProvider.createRefreshToken(payload)

        val validatedPayload = jwtProvider.validateRefreshToken(token)

        assertNotNull(validatedPayload)
        assertEquals(payload.username, validatedPayload?.username)
    }

    @Test
    fun `validateRefreshToken returns null if token is not valid`() {
        val token = "invalidToken"

        val validatedPayload = jwtProvider.validateRefreshToken(token)

        assertNull(validatedPayload)
    }

    @Test
    fun `parseBearerToken returns the parsed bearer token`() {
        val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImJvYiJ9.3Z5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQ"

        val parsedToken = jwtProvider.parseBearerToken(token)

        assertNotNull(parsedToken)
        assertEquals(
            "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImJvYiJ9.3Z5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQ",
            parsedToken
        )
    }

    @Test
    fun `validateRefreshToken throws SignatureException when token signature is wrongly verified`() {
        val username = "TestUsername1"
        val payload = JwtPayload.fromData(username)
        val refreshToken = jwtProvider.createRefreshToken(payload)

        assertThrows(SignatureException::class.java) {
            getTokenClaims(
                refreshToken.token,
                jwtProviderAccessTokenKey
            ).claims
        }
    }

    @Test
    fun `parseBearerToken returns null if its not a bearer token`() {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImJvYiJ9.3Z5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQZ5ZQ"

        val parsedToken = jwtProvider.parseBearerToken(token)

        assertNull(parsedToken)
    }

    // JwtPayload

    @Test
    fun `JwtPayload creation is successful`() {
        JwtPayload.fromData(username = "UsernameTest4")
    }

    @Test
    fun `JwtPayload to Claims conversion is successful`() {
        val jwtPayload = JwtPayload.fromData(username = "UsernameTest5")

        val claims = jwtPayload.claims

        assertEquals(jwtPayload.username, claims.subject)
    }

    @Test
    fun `JwtPayload from Claims conversion is successful`() {
        val claims = Jwts.claims().subject("TestUsername6").build()

        val jwtPayload = JwtPayload(claims)

        assertEquals(claims.subject, jwtPayload.username)
    }

    // RefreshTokenDetails

    @Test
    fun `RefreshTokenDetails creation is successful`() {
        JWTProvider.RefreshTokenDetails(token = "token", expirationDate = Timestamp.from(Instant.now()))
    }
}