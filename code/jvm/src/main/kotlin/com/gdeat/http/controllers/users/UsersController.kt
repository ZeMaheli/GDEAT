package com.gdeat.http.controllers.users

import com.gdeat.http.controllers.users.models.login.LoginInputModel
import com.gdeat.http.controllers.users.models.login.LoginOutputModel
import com.gdeat.http.controllers.users.models.refreshToken.RefreshTokenOutputModel
import com.gdeat.http.controllers.users.models.register.RegisterInputModel
import com.gdeat.http.controllers.users.models.register.RegisterOutputModel
import com.gdeat.http.media.siren.Link
import com.gdeat.http.media.siren.SirenEntity
import com.gdeat.http.media.siren.SirenEntity.Companion.sirenMediaType
import com.gdeat.http.pipeline.authentication.RequiresAuthentication
import com.gdeat.http.utils.PathTemplate
import com.gdeat.http.utils.Rels
import com.gdeat.service.users.UsersService
import com.gdeat.service.users.dtos.logout.LogoutInputDTO
import com.gdeat.service.users.dtos.token.RefreshTokenInputDTO
import com.gdeat.utils.JwtProvider
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Duration

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
    fun register(
        @RequestBody userData: RegisterInputModel,
        response: HttpServletResponse
    ): ResponseEntity<SirenEntity<RegisterOutputModel>> {
        val userRegistryDTO = usersServices.register(userData.toRegisterInputDTO())

        setTokenCookies(response, userRegistryDTO.accessToken, userRegistryDTO.refreshToken)

        val registerOutputEntity = SirenEntity(
            `class` = listOf(Rels.REGISTER),
            properties = RegisterOutputModel(userRegistryDTO),
            links = listOf(
                Link(rel = listOf(Rels.HOME), href = PathTemplate.home())
            ),
        )

        return registerOutputEntity.toResponse(HttpStatus.CREATED)
    }

    /**
     * Handles user login.
     *
     * @param userData The UserLoginInputModel representing the user login data.
     * @param response The HttpServletResponse to set the response headers.
     * @return ResponseEntity with the appropriate status and/or the new user tokens.
     */
    @PostMapping(PathTemplate.LOGIN)
    fun login(
        @RequestBody userData: LoginInputModel,
        response: HttpServletResponse
    ): ResponseEntity<SirenEntity<LoginOutputModel>> {
        val userLogInDTO = usersServices.login(userData.toLoginInputDTO())

        setTokenCookies(response, userLogInDTO.accessToken, userLogInDTO.refreshToken)

        val loginOutputEntity = SirenEntity(
            `class` = listOf(Rels.LOGIN),
            properties = LoginOutputModel(userLogInDTO),
            links = listOf(
                Link(rel = listOf(Rels.HOME), href = PathTemplate.home())
            ),
        )

        return loginOutputEntity.toResponse(HttpStatus.OK)
    }

    /**
     * Handles user logout.
     *
     * @param response The HttpServletResponse to set the response headers.
     *
     * @return ResponseEntity with the appropriate status and response body.
     */
    @PostMapping(PathTemplate.LOGOUT)
    @RequiresAuthentication
    fun logout(
        @RequestAttribute("access_token", required = true) accessToken: String,
        @RequestAttribute("refresh_token", required = true) refreshToken: String,
        response: HttpServletResponse
    ): ResponseEntity<SirenEntity<Unit>> {
        usersServices.logout(LogoutInputDTO(accessToken, refreshToken))

        clearTokenCookies(response)

        val logoutOutputEntity = SirenEntity(
            `class` = listOf(Rels.LOGOUT),
            properties = Unit,
            links = listOf(
                Link(rel = listOf(Rels.HOME), href = PathTemplate.home())
            ),
        )

        return logoutOutputEntity.toResponse(HttpStatus.OK)
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
        @RequestAttribute(JwtProvider.ACCESS_TOKEN_ATTRIBUTE, required = true) refreshToken: String,
        @RequestAttribute(JwtProvider.REFRESH_TOKEN_ATTRIBUTE, required = false) accessToken: String,
        response: HttpServletResponse
    ): ResponseEntity<SirenEntity<RefreshTokenOutputModel>> {
        val refreshTokenDTO = usersServices.refreshToken(RefreshTokenInputDTO(refreshToken,accessToken))

        setTokenCookies(response, refreshTokenDTO.accessToken, refreshTokenDTO.refreshToken)

        val refreshTokenEntity = SirenEntity(
            `class` = listOf(Rels.LOGIN),
            properties = RefreshTokenOutputModel(refreshTokenDTO),
            links = listOf(
                Link(rel = listOf(Rels.HOME), href = PathTemplate.home())
            ),
        )

        return refreshTokenEntity.toResponse(HttpStatus.OK)
    }

    /**
     * Helper method to set access and refresh token cookies in the HTTP response.
     *
     * @param response The HttpServletResponse to set the response headers.
     * @param accessToken The access token to be set as a cookie.
     * @param refreshToken The refresh token to be set as a cookie.
     */
    private fun setTokenCookies(
        response: HttpServletResponse,
        accessToken: String,
        refreshToken: String,
    ) {
        val accessTokenCookie = ResponseCookie.from("access_token", accessToken)
            .httpOnly(true)
            .path("/")
            .maxAge(Duration.ofHours(1))
            .sameSite("Strict")
            .build()

        val refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
            .httpOnly(true)
            .path("/")
            .maxAge(Duration.ofDays(1))
            .sameSite("Strict")
            .build()

        response.addCookie(accessTokenCookie)
        response.addCookie(refreshTokenCookie)
    }

    /**
     * Helper method to remove access and refresh token cookies of the HTTP response.
     *
     * @param response The HttpServletResponse to set the response headers.
     */
    private fun clearTokenCookies(response: HttpServletResponse) {
        val accessTokenCookie = ResponseCookie.from("access_token", "")
            .httpOnly(true)
            .maxAge(0)
            .sameSite("Strict")
            .build()

        val refreshTokenCookie = ResponseCookie.from("refresh_token", "")
            .httpOnly(true)
            .maxAge(0)
            .sameSite("Strict")
            .build()

        response.addCookie(accessTokenCookie)
        response.addCookie(refreshTokenCookie)
    }

    /**
     * Helper method to add cookies to the HTTP response.
     *
     * @param cookie The Response cookie to add to the response header.
     */
    private fun HttpServletResponse.addCookie(cookie: ResponseCookie) {
        this.addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
    }
}