package com.gdeat.http.controllers.users

import com.gdeat.http.controllers.users.models.register.RegisterInputModel
import com.gdeat.http.utils.PathTemplate
import com.gdeat.service.users.UsersService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller class for handling user-related operations in a RESTful manner.
 *
 * @property usersServices The service responsible for user-related business logic.
 */
@RestController
@RequestMapping()
class UserController(private val usersServices: UsersService) {
    /**
     * Handles user registration.
     *
     * @param s The UserRegisterInputModel representing the user registration data.
     * @param response The HttpServletResponse to set the response headers.
     * @return ResponseEntity with the appropriate status and/or the created user tokens.
     */
    @PostMapping(PathTemplate.REGISTER)
    fun register(@RequestBody s: RegisterInputModel, response: HttpServletResponse): ResponseEntity<*> {
        TODO()
    }
}