package com.gdeat.http.pipeline.authentication

import com.gdeat.service.exceptions.AuthenticationException
import com.gdeat.security.JWTProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

/**
 * Intercepts requests that need authentication.
 *
 * The interceptor checks:
 * 1. If the request has an Authorization header
 * 2. If the token in the header is a bearer token
 * 3. If the token is valid
 *
 * @property jwtProvider the JWT provider
 */
@Component
class AuthInterceptor(
    val jwtProvider: JWTProvider
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if (
            handler !is HandlerMethod || !handler.hasMethodAnnotation(RequiresAuthentication::class.java)
        ) return true

        val accessTokenAuthCookie = request.cookies?.firstOrNull { it.name == ACCESS_TOKEN_COOKIE_NAME }?.value
        val refreshToken = request.cookies?.firstOrNull { it.name == REFRESH_TOKEN_COOKIE_NAME }?.value

        val accessToken = accessTokenAuthCookie
            ?: (
                    jwtProvider.parseBearerToken(
                        request.getHeader(AUTHORIZATION_HEADER)
                            ?: throw AuthenticationException("Missing authorization token")
                    ) ?: throw AuthenticationException("Token is not a Bearer Token")
                    )

        request.setAttribute(JWTProvider.ACCESS_TOKEN_ATTRIBUTE, accessToken)

        if (refreshToken != null)
            request.setAttribute(JWTProvider.REFRESH_TOKEN_ATTRIBUTE, refreshToken)

        return true
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val ACCESS_TOKEN_COOKIE_NAME = "access_token"
        private const val REFRESH_TOKEN_COOKIE_NAME = "refresh_token"
    }
}