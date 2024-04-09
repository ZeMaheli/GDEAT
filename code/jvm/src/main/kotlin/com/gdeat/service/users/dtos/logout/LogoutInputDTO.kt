package com.gdeat.service.users.dtos.logout

/**
 * A logout Input DTO.
 *
 * @property accessToken the access token
 * @property refreshToken the refresh token
 */
data class LogoutInputDTO(
    val accessToken: String,
    val refreshToken: String
)