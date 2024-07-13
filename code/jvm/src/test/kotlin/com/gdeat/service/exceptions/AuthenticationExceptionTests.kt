package com.gdeat.service.exceptions

import com.gdeat.service.exceptions.AuthenticationException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AuthenticationExceptionTests {

    @Test
    fun `AuthenticationException creation is successful`() {
        AuthenticationException("Test")
    }

    @Test
    fun `AuthenticationException thrown successfully`() {
        assertThrows<AuthenticationException> {
            throw AuthenticationException("Test")
        }
    }
}
