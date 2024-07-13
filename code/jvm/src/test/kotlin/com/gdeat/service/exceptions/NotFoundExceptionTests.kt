package com.gdeat.service.exceptions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NotFoundExceptionTests {

    @Test
    fun `NotFoundException creation is successful`() {
        NotFoundException("Test")
    }

    @Test
    fun `NotFoundException thrown successfully`() {
        assertThrows<NotFoundException> {
            throw NotFoundException("Test")
        }
    }
}
