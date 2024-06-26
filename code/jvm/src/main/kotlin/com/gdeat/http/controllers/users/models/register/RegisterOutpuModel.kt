package com.gdeat.http.controllers.users.models.register

import com.gdeat.service.users.dtos.register.RegisterOutputDTO

/**
 * A Register Output Model.
 *
 * @property accessToken the access token of the user
 * @property refreshToken the refresh token of the user
 */
data class RegisterOutputModel(
    val accessToken: String,
    val refreshToken: String
) {
    constructor(registerOutputDTO: RegisterOutputDTO) : this(
        accessToken = registerOutputDTO.accessToken,
        refreshToken = registerOutputDTO.refreshToken
    )
}