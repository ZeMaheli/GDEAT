package com.gdeat.service.users.dtos.token

/**
 * A Token Input DTO.
 *
 * @property accessToken the access token
 * @property refreshToken the refresh token
 */
data class RefreshTokenInputDTO(
    val refreshToken: String,
    val accessToken: String
)