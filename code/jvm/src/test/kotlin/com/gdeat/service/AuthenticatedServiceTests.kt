package com.gdeat.service

import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import com.gdeat.domain.users.UserTests.Companion.defaultUser
import com.gdeat.repository.tokens.RevokedAccessTokenRepository
import com.gdeat.repository.users.UsersRepository
import com.gdeat.security.JWTProvider
import com.gdeat.security.SecurityConfig
import com.gdeat.service.exceptions.AuthenticationException
import com.gdeat.service.exceptions.NotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticatedServiceTests {

    @MockBean
    private lateinit var usersRepository: UsersRepository

    @Autowired
    private lateinit var jwtProvider: JWTProvider

    @MockBean
    private lateinit var revokedAccessTokensRepository: RevokedAccessTokenRepository

    @Autowired
    private lateinit var hashingUtils: SecurityConfig

    private val authenticatedService: AuthenticationService by lazy {
        object :
            AuthenticationService(
                usersRepository,
                revokedAccessTokensRepository,
                jwtProvider,
                hashingUtils
            ) {}
    }

    @Test
    fun `authenticateUser returns the authenticated user`() {
        val user = defaultUser

        Mockito.`when`(usersRepository.findByUsername(defaultUser.username))
            .thenReturn(user)

        val token = jwtProvider.createAccessToken(
            jwtPayload = JWTProvider.JwtPayload.fromData(username = defaultUser.username)
        )

        Mockito.`when`(
            revokedAccessTokensRepository.findByUserAndTokenHash(
                user,
                tokenHash =
                hashingUtils.hashToken(token)
            )
        ).thenReturn(null)

        val authenticatedUser = authenticatedService.authenticateUser(
            token = token
        )

        assertEquals(user, authenticatedUser)
    }

    @Test
    fun `authenticateUser throws AuthenticationException if the token is invalid`() {
        assertThrows<AuthenticationException> {
            authenticatedService.authenticateUser(
                token = "invalidToken"
            )
        }
    }

    @Test
    fun `authenticateUser throws NotFoundException if the user does not exist`() {
        assertThrows<NotFoundException> {
            authenticatedService.authenticateUser(
                token = jwtProvider.createAccessToken(
                    jwtPayload = JWTProvider.JwtPayload.fromData(username = defaultUser.username)
                )
            )
        }
    }
}
