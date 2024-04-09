package com.gdeat.service.users

import com.gdeat.repository.users.RefreshTokensRepository
import com.gdeat.repository.users.UsersRepository
import com.gdeat.service.users.dtos.login.LoginInputDTO
import com.gdeat.service.users.dtos.login.LoginOutputDTO
import com.gdeat.service.users.dtos.register.RegisterInputDTO
import com.gdeat.service.users.dtos.register.RegisterOutputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenOutputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenInputDTO
import org.springframework.stereotype.Service

/**
 * Service class providing user-related functionality.
 */
@Service
class UsersServiceImpl(
    private val usersRepository: UsersRepository,
    private val refreshTokensRepository: RefreshTokensRepository,
): UsersService {
    override fun register(registerInputDTO: RegisterInputDTO): RegisterOutputDTO {
        TODO("Not yet implemented")
    }

    override fun login(loginInputDTO: LoginInputDTO): LoginOutputDTO {
        TODO("Not yet implemented")
    }

    override fun logout(tokenInputDTO: RefreshTokenInputDTO) {
        TODO("Not yet implemented")
    }

    override fun refreshToken(tokenInputDTO: RefreshTokenInputDTO): RefreshTokenOutputDTO {
        TODO("Not yet implemented")
    }
}