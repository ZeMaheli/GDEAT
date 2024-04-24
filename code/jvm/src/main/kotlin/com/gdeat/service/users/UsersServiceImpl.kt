package com.gdeat.service.users

import com.gdeat.domain.users.RefreshToken
import com.gdeat.domain.users.RevokedAccessToken
import com.gdeat.domain.users.User
import com.gdeat.repository.users.RefreshTokensRepository
import com.gdeat.repository.users.RevokedAccessTokensRepository
import com.gdeat.repository.users.UsersRepository
import com.gdeat.service.exceptions.*
import com.gdeat.service.users.dtos.login.LoginInputDTO
import com.gdeat.service.users.dtos.login.LoginOutputDTO
import com.gdeat.service.users.dtos.logout.LogoutInputDTO
import com.gdeat.service.users.dtos.register.RegisterInputDTO
import com.gdeat.service.users.dtos.register.RegisterOutputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenInputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenOutputDTO
import com.gdeat.service.utils.SecurityConfig
import com.gdeat.utils.JwtProvider
import com.gdeat.utils.ServerConfiguration
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.time.Instant

/**
 * Service class providing user-related functionality.
 */
@Service
@Transactional(rollbackFor = [Exception::class])
class UsersServiceImpl(
    private val usersRepository: UsersRepository,
    private val refreshTokensRepository: RefreshTokensRepository,
    private val revokedAccessTokensRepository: RevokedAccessTokensRepository,
    private val securityConfig: SecurityConfig,
    private val jwtProvider: JwtProvider,
    private val serverConfig: ServerConfiguration,
) : UsersService {

    override fun register(registerInputDTO: RegisterInputDTO): RegisterOutputDTO {
        val username = registerInputDTO.username
        val password = registerInputDTO.password
        val email = registerInputDTO.email

        if (usersRepository.existsByUsername(username)) {
            throw AlreadyExistsException("User with $username username already exists")
        }

        if (usersRepository.existsByEmail(email)) {
            throw AlreadyExistsException("User with $email email already exists")
        }

        if (password.length < 12) {
            throw InvalidPasswordException("Invalid password length. Must be at least 12 characters long")
        }

        val user = usersRepository.save(
            User(
                username = username,
                passwordHash = securityConfig.hashPassword(username, password),
                email = email
            )
        )

        val (accessToken, refreshToken) = createTokens(user = user)

        return RegisterOutputDTO(
            username = registerInputDTO.username,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun login(loginInputDTO: LoginInputDTO): LoginOutputDTO {
        val username = loginInputDTO.username
        val password = loginInputDTO.password

        val user = usersRepository
            .findByUsername(username = username)
            ?: throw InvalidLoginException("Invalid username or password")

        if (
            !securityConfig.verifyHashPassword(
                username = username,
                rawPassword = password,
                encodedPassword = user.passwordHash
            )
        ) throw InvalidLoginException("Invalid username or password")

        val (accessToken, refreshToken) = createTokens(user = user)

        return LoginOutputDTO(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun logout(tokenInputDTO: LogoutInputDTO) {
        val user = getUserAndRevokeAccessToken(accessToken = tokenInputDTO.accessToken)

        val refreshTokenEntity = refreshTokensRepository
            .findByUserAndTokenHash(
                user = user,
                tokenHash = securityConfig.hashToken(token = tokenInputDTO.refreshToken)
            )
            ?: throw NotFoundException("Refresh token not found")

        refreshTokensRepository.delete(refreshTokenEntity)
    }

    override fun refreshToken(tokenInputDTO: RefreshTokenInputDTO): RefreshTokenOutputDTO {
        val user = getUserAndRevokeAccessToken(accessToken = tokenInputDTO.accessToken)

        val refreshTokenHash = securityConfig.hashToken(token = tokenInputDTO.refreshToken)

        val refreshTokenEntity = refreshTokensRepository
            .findByUserAndTokenHash(
                user = user,
                tokenHash = refreshTokenHash
            )
            ?: throw NotFoundException("Refresh token not found")

        refreshTokensRepository.delete(refreshTokenEntity)

        if (refreshTokenEntity.expirationDate.before(Timestamp.from(Instant.now())))
            throw RefreshTokenExpiredException("Refresh token expired")

        val (accessToken, newRefreshToken) = createTokens(user = user)

        return RefreshTokenOutputDTO(
            accessToken = accessToken,
            refreshToken = newRefreshToken
        )
    }

    /**
     * Creates the access and refresh tokens for the given user.
     *
     * @param user the user to create the tokens for
     * @return the access and refresh tokens
     */
    private fun createTokens(user: User): Tokens {
        if (refreshTokensRepository.countByUser(user = user) >= serverConfig.maxRefreshTokens) {
            refreshTokensRepository
                .getRefreshTokensOfUser(user).first()
                .also { refreshTokensRepository.delete(it) }
        }

        val jwtPayload = JwtProvider.JwtPayload.fromData(username = user.username)
        val accessToken = jwtProvider.createAccessToken(jwtPayload = jwtPayload)
        val (refreshToken, expirationDate) = jwtProvider.createRefreshToken(jwtPayload = jwtPayload)

        val refreshTokenHash = securityConfig.hashToken(token = refreshToken)

        refreshTokensRepository.save(
            RefreshToken(
                user = user,
                tokenHash = refreshTokenHash,
                expirationDate = expirationDate
            )
        )

        return Tokens(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    /**
     * Gets the user from the access token and revokes the token.
     */
    private fun getUserAndRevokeAccessToken(accessToken: String): User {
        val accessTokenPayload = jwtProvider.getAccessTokenPayloadOrNull(token = accessToken)
            ?: throw AuthenticationException("Invalid access token")

        val user = usersRepository.findByUsername(username = accessTokenPayload.username)
            ?: throw NotFoundException("User not found")

        val revokedAccessTokenEntity = RevokedAccessToken(
            tokenHash = securityConfig.hashToken(token = accessToken),
            user = user,
            expirationDate = Timestamp.from(accessTokenPayload.claims.expiration.toInstant())
        )

        revokedAccessTokensRepository.save(revokedAccessTokenEntity)
        return user
    }

    /**
     * The tokens of a user.
     *
     * @property accessToken the access token
     * @property refreshToken the refresh token
     */
    private data class Tokens(
        val accessToken: String,
        val refreshToken: String
    )
}