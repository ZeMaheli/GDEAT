package com.gdeat.http.controllers.users.models.login

import com.gdeat.service.users.dtos.login.LoginOutputDTO

/**
 * A Login Output Model.
 *
 * @property accessToken the access token of the user
 * @property refreshToken the refresh token of the user
 */
data class LoginOutputModel(
    val accessToken: String,
    val refreshToken: String
) {
    constructor(loginOutputDTO: LoginOutputDTO) : this(
        accessToken = loginOutputDTO.accessToken,
        refreshToken = loginOutputDTO.refreshToken
    )
}