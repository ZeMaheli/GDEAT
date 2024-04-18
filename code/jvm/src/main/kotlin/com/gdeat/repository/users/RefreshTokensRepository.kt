package com.gdeat.repository.users

import com.gdeat.domain.users.RefreshToken
import com.gdeat.domain.users.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository for the [RefreshToken] entity
 */
interface RefreshTokensRepository : JpaRepository<RefreshToken, Long> {
    /**
     * Finds a [RefreshToken] given a user and the token hash.
     *
     * @param user the user
     * @param tokenHash the token hash
     *
     * @return the [RefreshToken] if found, null otherwise
     */
    fun findByUserAndTokenHash(user: User, tokenHash: String): RefreshToken?
}