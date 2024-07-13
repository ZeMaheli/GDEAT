package com.gdeat.service.exceptions

import com.gdeat.service.exceptions.AIServerException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AIServerExceptionTests {

    @Test
    fun `AIServerException creation is successful`() {
        AIServerException("Test")
    }

    @Test
    fun `AIServerException thrown successfully`() {
        assertThrows<AIServerException> {
            throw AIServerException("Test")
        }
    }
}
