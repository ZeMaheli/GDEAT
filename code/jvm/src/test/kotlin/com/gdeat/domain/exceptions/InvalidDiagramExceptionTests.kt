package com.gdeat.domain.exceptions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InvalidDiagramExceptionTests {

    @Test
    fun `InvalidDiagramException creation is successful`() {
        InvalidDiagramException("Test")
    }

    @Test
    fun `InvalidDiagramException thrown successfully`() {
        assertThrows<InvalidDiagramException> {
            throw InvalidDiagramException("Test")
        }
    }
}