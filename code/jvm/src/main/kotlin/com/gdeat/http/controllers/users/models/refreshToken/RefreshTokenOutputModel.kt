package com.gdeat.http.controllers.users.models.refreshToken

import com.gdeat.service.users.dtos.token.RefreshTokenOutputDTO

/**
 * The Refresh Token Output Model.
 *
 * @property accessToken the access token
 * @property refreshToken the refresh token
 */
data class RefreshTokenOutputModel(
    val accessToken: String,
    val refreshToken: String
) {
    constructor(refreshTokenOutputDTO: RefreshTokenOutputDTO) : this(
        accessToken = refreshTokenOutputDTO.accessToken,
        refreshToken = refreshTokenOutputDTO.refreshToken
    )
}