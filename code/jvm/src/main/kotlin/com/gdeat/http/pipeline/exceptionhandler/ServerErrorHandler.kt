package com.gdeat.http.pipeline.exceptionhandler

import com.gdeat.http.media.error.JsonError
import com.gdeat.service.exceptions.AIServiceException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ServerErrorHandler {
    /**
     * Handles all other uncaught exceptions related to server.
     *
     * @param ex exception to handle
     * @param request the HTTP request
     * @return response entity with the error message
     */
    @ExceptionHandler(
        value = [
            Exception::class,
            AIServiceException::class
        ]
    )
    fun handleUncaughtExceptions(
        request: HttpServletRequest,
        ex: Exception
    ): ResponseEntity<JsonError> =
        JsonError(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "Internal Server Error",
            details = ex.message
        ).toResponse(HttpStatus.INTERNAL_SERVER_ERROR)
}