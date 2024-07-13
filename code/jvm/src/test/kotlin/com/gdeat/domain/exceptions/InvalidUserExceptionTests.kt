package com.gdeat.domain.exceptions

import com.gdeat.domain.exceptions.InvalidUserException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class InvalidUserExceptionTests {

    @Test
    fun `InvalidUserException creation is successful`() {
        InvalidUserException("Test")
    }

    @Test
    fun `InvalidUserException thrown successfully`() {
        assertThrows<InvalidUserException> {
            throw InvalidUserException("Test")
        }
    }
}
