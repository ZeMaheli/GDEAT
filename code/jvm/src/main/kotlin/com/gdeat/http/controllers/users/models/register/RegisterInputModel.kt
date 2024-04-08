package com.gdeat.http.controllers.users.models.register

import com.gdeat.service.users.dtos.register.RegisterInputDTO

/**
 * A Register Input Model.
 *
 * @property username the username of the user to be created
 * @property email the email of the user to be created
 * @property password the password of the user to be created
 */
data class RegisterInputModel(
    val username: String,
    val email: String,
    val password: String
){
    /**
     * Converts this model to a service DTO.
     *
     * @return the service DTO
     */
    fun toRegisterInputDTO() = RegisterInputDTO(
        username = username,
        email = email,
        password = password
    )
}
