package com.gdeat.http.pipeline.authentication

import com.gdeat.security.JWTProvider
import com.gdeat.service.exceptions.AuthenticationException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.method.HandlerMethod


@SpringBootTest
class AuthenticationInterceptorTests {

    @Autowired
    lateinit var jwtProvider: JWTProvider

    private open class TestController

    private class UnauthenticatedTestController : TestController() {
        @Suppress("unused")
        fun test() {
        }
    }

    private class AuthenticatedTestController : TestController() {
        @Suppress("unused")
        @RequiresAuthentication
        fun test() {
        }
    }

    /**
     * Returns the HandlerMethod for testing.
     *
     * @param T the controller class (must derive from TestController in this testing environment)
     */
    private inline fun <reified T : TestController> handlerMethod() = HandlerMethod(
        /* bean = */ T::class,
        /* method = */ T::class.java.getMethod("test")
    )

    @Test
    fun `AuthenticationInterceptor creation is successful`() {
        AuthInterceptor(jwtProvider)
    }

    @Test
    fun `preHandle returns true if the handler is not a HandlerMethod`() {
        val authenticationInterceptor = AuthInterceptor(jwtProvider)
        val httpServletRequest = MockHttpServletRequest()
        val httpServletResponse = MockHttpServletResponse()

        val proceed = authenticationInterceptor.preHandle(
            httpServletRequest,
            httpServletResponse,
            1
        )

        assertTrue(proceed)
    }

    @Test
    fun `preHandle returns true if the handler is HandlerMethod without Authenticated annotation`() {
        val authenticationInterceptor = AuthInterceptor(jwtProvider)
        val handlerMethod = handlerMethod<UnauthenticatedTestController>()
        val httpServletRequest = MockHttpServletRequest()
        val httpServletResponse = MockHttpServletResponse()

        val proceed = authenticationInterceptor.preHandle(
            request = httpServletRequest,
            response = httpServletResponse,
            handler = handlerMethod
        )

        assertTrue(proceed)
    }

    @Test
    fun `preHandle returns false and sends an error response if the handler has Authenticated annotation but is missing Authorization Header`() {
        val authenticationInterceptor = AuthInterceptor(jwtProvider)
        val handlerMethod = handlerMethod<AuthenticatedTestController>()
        val httpServletRequest = MockHttpServletRequest()
        val httpServletResponse = MockHttpServletResponse()

        assertThrows<AuthenticationException> {
            authenticationInterceptor.preHandle(
                request = httpServletRequest,
                response = httpServletResponse,
                handler = handlerMethod
            )
        }
    }

    @Test
    fun `preHandle returns false and sends an error response if the handler has Authenticated annotation but the token is not a bearer token`() {
        val authenticationInterceptor = AuthInterceptor(jwtProvider)
        val handlerMethod = handlerMethod<AuthenticatedTestController>()
        val httpServletRequest = MockHttpServletRequest().also {
            it.addHeader(
                /* name = */ "Authorization",
                /* value = */ "invalidToken"
            )
        }
        val httpServletResponse = MockHttpServletResponse()

        assertThrows<AuthenticationException> {
            authenticationInterceptor.preHandle(
                request = httpServletRequest,
                response = httpServletResponse,
                handler = handlerMethod
            )
        }
    }

    @Test
    fun `preHandle returns true and stores a token attribute if the handler has Authenticated annotation and the token is a bearer token`() {
        val authenticationInterceptor = AuthInterceptor(jwtProvider)
        val handlerMethod = handlerMethod<AuthenticatedTestController>()
        val httpServletRequest = MockHttpServletRequest().also {
            it.addHeader(
                /* name = */ "Authorization",
                /* value = */ "Bearer bearerToken"
            )
        }
        val httpServletResponse = MockHttpServletResponse()

        val proceed = authenticationInterceptor.preHandle(
            request = httpServletRequest,
            response = httpServletResponse,
            handler = handlerMethod
        )

        assertEquals("bearerToken", httpServletRequest.getAttribute("access_token"))
        assertTrue(proceed)
    }
}
