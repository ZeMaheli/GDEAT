package com.gdeat.service.users

import com.gdeat.service.users.dtos.login.LoginInputDTO
import com.gdeat.service.users.dtos.login.LoginOutputDTO
import com.gdeat.service.users.dtos.register.RegisterInputDTO
import com.gdeat.service.users.dtos.register.RegisterOutputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenOutputDTO
import com.gdeat.service.users.dtos.token.TokenInputDTO
import org.springframework.stereotype.Service

@Service
class UsersServiceImpl: UsersService {
    override fun register(registerInputDTO: RegisterInputDTO): RegisterOutputDTO {
        TODO("Not yet implemented")
    }

    override fun login(loginInputDTO: LoginInputDTO): LoginOutputDTO {
        TODO("Not yet implemented")
    }

    override fun logout(tokenInputDTO: TokenInputDTO) {
        TODO("Not yet implemented")
    }

    override fun refreshToken(tokenInputDTO: TokenInputDTO): RefreshTokenOutputDTO {
        TODO("Not yet implemented")
    }
}