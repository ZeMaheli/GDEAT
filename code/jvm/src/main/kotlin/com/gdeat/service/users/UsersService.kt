package com.gdeat.service.users

import com.gdeat.service.users.dtos.login.LoginInputDTO
import com.gdeat.service.users.dtos.login.LoginOutputDTO
import com.gdeat.service.users.dtos.logout.LogoutInputDTO
import com.gdeat.service.users.dtos.register.RegisterInputDTO
import com.gdeat.service.users.dtos.register.RegisterOutputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenOutputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenInputDTO

interface UsersService {
    /**
     * Registers a new user.
     *
     * @param registerInputDTO the DTO with the data to create the user
     *
     * @return the JWT tokens for the new user
     */
    fun register(registerInputDTO: RegisterInputDTO): RegisterOutputDTO

    /**
     * Logs a user in.
     *
     * @param loginInputDTO the DTO with the data to log the user in
     *
     * @return the JWT tokens for the user
     */
    fun login(loginInputDTO: LoginInputDTO): LoginOutputDTO

    /**
     * Logs a user out.
     *
     * @param tokenInputDTO the DTO with the tokens necessary to logout
     */
    fun logout(tokenInputDTO: LogoutInputDTO)

    /**
     * Refreshes the JWT token of a user.
     *
     * @param tokenInputDTO the DTO with the tokens
     *
     * @return the new JWT token for the user
     */
    fun refreshToken(tokenInputDTO: RefreshTokenInputDTO): RefreshTokenOutputDTO
}