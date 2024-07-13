package com.gdeat.service.exceptions

import com.gdeat.service.exceptions.InvalidLoginException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InvalidLoginExceptionTests {

    @Test
    fun `InvalidLoginException creation is successful`() {
        InvalidLoginException("Test")
    }

    @Test
    fun `InvalidLoginException thrown successfully`() {
        assertThrows<InvalidLoginException> {
            throw InvalidLoginException("Test")
        }
    }
}
