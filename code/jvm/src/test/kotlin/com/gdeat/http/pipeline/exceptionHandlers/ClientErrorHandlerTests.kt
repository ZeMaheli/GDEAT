package com.gdeat.http.pipeline.exceptionHandlers

import com.gdeat.http.pipeline.exceptionhandler.ClientErrorHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.parsing.Problem
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.validation.DataBinder
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import sirenentity.error.JsonError

@SpringBootTest
class ClientErrorHandlerTests {

    @Autowired
    lateinit var handler: ClientErrorHandler

    @Test
    fun `handleBadRequest returns ResponseEntity with BadRequest error`() {
        val httpServletRequest = MockHttpServletRequest()

        val responseEntity = handler.handleBadRequest(httpServletRequest, Exception("test"))

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.statusCode)

        assertEquals("Bad Request", (responseEntity.body as JsonError).message)
        assertEquals("test", (responseEntity.body as JsonError).details )
    }

    @Test
    fun `handleUnauthorized returns ResponseEntity with Unauthorized error`() {
        val httpServletRequest = MockHttpServletRequest()

        val responseEntity = handler.handleUnauthorized(httpServletRequest, Exception("test"))

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.statusCode)
        assertEquals("Unauthorized", (responseEntity.body as JsonError).message)
        assertEquals("test", (responseEntity.body as JsonError).details )
    }
}