package com.gdeat.http.pipeline.exceptionHandlers

import com.gdeat.http.pipeline.exceptionhandler.ClientErrorHandler
import com.gdeat.http.pipeline.exceptionhandler.ServerErrorHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import sirenentity.error.JsonError

@SpringBootTest
class ServerErrorHandlerTests {
    @Autowired
    lateinit var handler: ServerErrorHandler

    @Test
    fun `handleUncaughtRequest returns ResponseEntity with BadRequest error`() {
        val httpServletRequest = MockHttpServletRequest()

        val responseEntity = handler.handleUncaughtExceptions(httpServletRequest, Exception("test"))

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.statusCode.value())

        assertEquals("Internal Server Error", (responseEntity.body as JsonError).message)
        assertEquals("test", (responseEntity.body as JsonError).details )
    }

}