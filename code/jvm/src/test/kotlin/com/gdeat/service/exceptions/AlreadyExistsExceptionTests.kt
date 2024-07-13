package com.gdeat.service.exceptions

import com.gdeat.service.exceptions.AlreadyExistsException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AlreadyExistsExceptionTests {

    @Test
    fun `AlreadyExistsException creation is successful`() {
        AlreadyExistsException("Test")
    }

    @Test
    fun `AlreadyExistsException thrown successfully`() {
        assertThrows<AlreadyExistsException> {
            throw AlreadyExistsException("Test")
        }
    }
}
