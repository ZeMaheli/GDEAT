package com.gdeat.service.users.dtos.token

/**
 * A Refresh Token Output DTO.
 *
 * @property accessToken the access token
 * @property refreshToken the refresh token
 */
data class RefreshTokenOutputDTO(
    val accessToken: String,
    val refreshToken: String
)