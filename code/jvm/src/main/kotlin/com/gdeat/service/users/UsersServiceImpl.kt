package com.gdeat.service.users

import com.gdeat.domain.users.User
import com.gdeat.repository.users.RefreshTokensRepository
import com.gdeat.repository.users.UsersRepository
import com.gdeat.service.users.dtos.login.LoginInputDTO
import com.gdeat.service.users.dtos.login.LoginOutputDTO
import com.gdeat.service.users.dtos.logout.LogoutInputDTO
import com.gdeat.service.users.dtos.register.RegisterInputDTO
import com.gdeat.service.users.dtos.register.RegisterOutputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenInputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenOutputDTO
import com.gdeat.service.utils.SecurityConfig
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

/**
 * Service class providing user-related functionality.
 */
@Service
class UsersServiceImpl(
    private val usersRepository: UsersRepository,
    private val refreshTokensRepository: RefreshTokensRepository,
    private val securityConfig: SecurityConfig,
) : UsersService {
    override fun register(registerInputDTO: RegisterInputDTO): RegisterOutputDTO {
        val username = registerInputDTO.username
        val password = registerInputDTO.password
        val email = registerInputDTO.email

        if (usersRepository.existsByUsername(username)) {

        }

        if (usersRepository.existsByEmail(email)) {

        }
        if (password.length < 12) {

        }

        val user = usersRepository.save(User(username = username, passwordHash = securityConfig.hashPassword(username,password), email = email))

        val (accessToken, refreshToken) = createTokens(user = user)

        return RegisterOutputDTO(
            username = registerInputDTO.username,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun login(loginInputDTO: LoginInputDTO): LoginOutputDTO {
        TODO("Not yet implemented")
    }

    override fun logout(tokenInputDTO: LogoutInputDTO) {
        TODO("Not yet implemented")
    }

    override fun refreshToken(tokenInputDTO: RefreshTokenInputDTO): RefreshTokenOutputDTO {
        TODO("Not yet implemented")
    }

    /**
     * Creates the access and refresh tokens for the given user.
     *
     * @param user the user to create the tokens for
     * @return the access and refresh tokens
     */
    private fun createTokens(user: User): Tokens {
        if (refreshTokensRepository.countByUser(user = user) >= config.maxRefreshTokens) {
            refreshTokensRepository
                .getRefreshTokensOfUserOrderedByExpirationDate(
                    user = user,
                    pageable = PageRequest.of(/* page = */ 0, /* size = */ 1)
                )
                .get()
                .findFirst()
                .ifPresent { refreshTokensRepository.delete(it) }
        }

        val jwtPayload = JwtPayload.fromData(username = user.username)
        val accessToken = jwtProvider.createAccessToken(jwtPayload = jwtPayload)
        val (refreshToken, expirationDate) = jwtProvider.createRefreshToken(jwtPayload = jwtPayload)

        val refreshTokenHash = hashingUtils.hashToken(token = refreshToken)

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
}