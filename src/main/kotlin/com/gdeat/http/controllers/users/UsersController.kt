package com.gdeat.http.controllers.users

import com.gdeat.http.controllers.users.models.login.LoginInputModel
import com.gdeat.http.controllers.users.models.register.RegisterInputModel
import com.gdeat.http.utils.PathTemplate
import com.gdeat.service.users.UsersService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
     * @param userData The UserRegisterInputModel representing the user registration data.
     * @param response The HttpServletResponse to set the response headers.
     * @return ResponseEntity with the appropriate status and/or the created user tokens.
     */
    @PostMapping(PathTemplate.REGISTER)
    fun register(@RequestBody userData: RegisterInputModel, response: HttpServletResponse): ResponseEntity<*> {
        TODO()
    }

    /**
     * Handles user login.
     *
     * @param userData The UserLoginInputModel representing the user login data.
     * @param response The HttpServletResponse to set the response headers.
     * @return ResponseEntity with the appropriate status and/or the new user tokens.
     */
    @PostMapping(PathTemplate.LOGIN)
    fun login(@RequestBody userData: LoginInputModel, response: HttpServletResponse): ResponseEntity<*> {
        TODO()
    }

    /**
     * Handles user logout.
     *
     * @return ResponseEntity with the appropriate status and response body.
     */
    @PostMapping(PathTemplate.LOGOUT)
    fun logout(
        @RequestAttribute("access_token", required = true) accessToken: String,
        @RequestAttribute("refresh_token", required = true) refreshToken: String
    ): ResponseEntity<*> {
            TODO()
    }

    /**
     * Handles refreshing the access token using the refresh token.
     *
     * @param refreshToken The refresh token obtained from the request attribute.
     * @param response The HttpServletResponse to set the response headers.
     * @return ResponseEntity with the appropriate status and/or the new user tokens.
     */
    @PostMapping(PathTemplate.REFRESH_TOKEN)
    fun refreshToken(
        @RequestAttribute("refresh_token", required = true) refreshToken: String,
        response: HttpServletResponse
    ): ResponseEntity<*> {
        TODO()
    }
}