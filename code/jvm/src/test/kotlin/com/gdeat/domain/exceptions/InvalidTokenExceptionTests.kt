package com.gdeat.domain.exceptions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InvalidTokenExceptionTests {
    @Test
    fun `InvalidTokenException creation is successful`() {
        InvalidTokenException("Test")
    }

    @Test
    fun `InvalidTokenException thrown successfully`() {
        assertThrows<InvalidTokenException> {
            throw InvalidTokenException("Test")
        }
    }
}