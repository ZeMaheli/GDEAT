package com.gdeat.http.controllers.users.models.login

import com.gdeat.service.users.dtos.login.LoginInputDTO

/**
 * A Login Input Model.
 *
 * @property username the username of the user
 * @property password the password of the user
 */
data class LoginInputModel(
    val username: String,
    val password: String
) {

    /**
     * Converts this model to a service DTO.
     *
     * @return the service DTO
     */
    fun toLoginInputDTO() = LoginInputDTO(
        username = username,
        password = password
    )
}