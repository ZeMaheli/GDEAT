package com.gdeat.service.exceptions

import com.gdeat.service.exceptions.InvalidPasswordException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InvalidPasswordExceptionTests {

    @Test
    fun `InvalidPasswordException creation is successful`() {
        InvalidPasswordException("Test")
    }

    @Test
    fun `InvalidPasswordException thrown successfully`() {
        assertThrows<InvalidPasswordException> {
            throw InvalidPasswordException("Test")
        }
    }
}
