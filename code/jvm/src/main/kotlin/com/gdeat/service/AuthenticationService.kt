package com.gdeat.service

import com.gdeat.domain.users.User
import com.gdeat.repository.tokens.RevokedAccessTokenRepository
import com.gdeat.repository.users.UsersRepository
import com.gdeat.service.exceptions.AuthenticationException
import com.gdeat.service.exceptions.NotFoundException
import com.gdeat.security.SecurityConfig
import com.gdeat.security.JWTProvider
import org.springframework.stereotype.Service

/**
 * Service that handles the authentication of the users.
 *
 * @property usersRepository the repository of the users
 * @property jwtProvider the JWT provider
 */
@Service
abstract class AuthenticationService(
    private val usersRepository: UsersRepository,
    private val revokedAccessTokenRepository: RevokedAccessTokenRepository,
    private val jwtProvider: JWTProvider,
    private val hashingUtils: SecurityConfig
) {

    /**
     * Authenticates a user.
     *
     * @param token the token of the user
     *
     * @return the authenticated user
     * @throws AuthenticationException if the token is invalid
     * @throws NotFoundException if the user is not found
     */
    fun authenticateUser(token: String): User {
        val tokenPayload = jwtProvider.validateAccessToken(token)
            ?: throw AuthenticationException("Invalid token")

        val user = usersRepository.findByUsername(tokenPayload.username)
            ?: throw NotFoundException("User not found")

        if (revokedAccessTokenRepository.existsByUserAndTokenHash(user, hashingUtils.hashToken(token)))
            throw AuthenticationException("Token is revoked")

        return user
    }
}