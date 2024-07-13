package com.gdeat.service.exceptions

import com.gdeat.service.exceptions.RefreshTokenExpiredException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RefreshTokenExpiredExceptionTests {

    @Test
    fun `RefreshTokenExpiredException creation is successful`() {
        RefreshTokenExpiredException("Test")
    }

    @Test
    fun `RefreshTokenExpiredException thrown successfully`() {
        assertThrows<RefreshTokenExpiredException> {
            throw RefreshTokenExpiredException("Test")
        }
    }
}
