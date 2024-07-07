package com.gdeat.http.pipeline.exceptionhandler

import com.gdeat.domain.exceptions.InvalidDiagramException
import com.gdeat.domain.exceptions.InvalidTokenException
import com.gdeat.domain.exceptions.InvalidUserException
import com.gdeat.http.media.error.JsonError
import com.gdeat.service.exceptions.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Handles exceptions thrown by the controllers.
 */
@ControllerAdvice
class ClientErrorHandler {

    /**
     * Handles Bad Request exceptions.
     *
     * @param ex exception to handle
     * @param request the HTTP request
     * @return response entity with the error message
     */
    @ExceptionHandler(
        value = [
            AlreadyExistsException::class,
            InvalidPasswordException::class,
            NotFoundException::class,
            RefreshTokenExpiredException::class,
            InvalidLoginException::class,
            InvalidTokenException::class,
            InvalidUserException::class,
            InvalidDiagramException::class
        ]
    )
    fun handleBadRequest(
        request: HttpServletRequest,
        ex: Exception
    ): ResponseEntity<JsonError> =
        JsonError(
            code = HttpStatus.BAD_REQUEST.value(),
            message = "Bad Request",
            details = ex.message
        ).toResponse(HttpStatus.BAD_REQUEST)

    /**
     * Handles Unauthorized exceptions.
     *
     * @param ex exception to handle
     * @param request the HTTP request
     * @return response entity with the error message
     */
    @ExceptionHandler(value = [AuthenticationException::class])
    fun handleUnauthorized(
        request: HttpServletRequest,
        ex: Exception
    ): ResponseEntity<JsonError> =
        JsonError(
            code = HttpStatus.UNAUTHORIZED.value(),
            message = "Unauthorized",
            details = ex.message
        ).toResponse(HttpStatus.UNAUTHORIZED)
}