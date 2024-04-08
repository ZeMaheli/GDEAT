package com.gdeat.repository.users

import com.gdeat.domain.users.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository for the [RefreshToken] entity
 */
interface RefreshTokensRepository : JpaRepository<RefreshToken, Long>