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
     * @throws AlreadyExistsException if the user already exists
     * @throws InvalidPasswordException if the password is invalid
     */
    fun register(registerInputDTO: RegisterInputDTO): RegisterOutputDTO

    /**
     * Logs a user in.
     *
     * @param loginInputDTO the DTO with the data to log the user in
     *
     * @return the JWT tokens for the user
     * @throws NotFoundException if the user does not exist
     * @throws InvalidLoginException if the password is incorrect
     */
    fun login(loginInputDTO: LoginInputDTO): LoginOutputDTO

    /**
     * Logs a user out.
     *
     * @param tokenInputDTO the DTO with the tokens necessary to logout
     *
     * @throws NotFoundException if the refresh token does not exist or if it is expired
     * @throws AuthenticationException if the refresh token is invalid
     */
    fun logout(tokenInputDTO: LogoutInputDTO)

    /**
     * Refreshes the JWT token of a user.
     *
     * @param tokenInputDTO the DTO with the tokens
     *
     * @return the new JWT token for the user
     * @throws NotFoundException if the refresh token does not exist
     * @throws RefreshTokenExpiredException if the refresh token is expired
     * @throws AuthenticationException if the refresh token is invalid
     */
    fun refreshToken(tokenInputDTO: RefreshTokenInputDTO): RefreshTokenOutputDTO
}