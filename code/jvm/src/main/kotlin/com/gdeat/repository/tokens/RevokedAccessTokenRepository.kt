package com.gdeat.repository.tokens

import com.gdeat.domain.users.RevokedAccessToken
import com.gdeat.domain.users.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository for the [RevokedAccessToken] entity.
 */
interface RevokedAccessTokenRepository : JpaRepository<RevokedAccessToken, Long> {
    /**
     * Finds a [RevokedAccessToken] given a user and the token hash.
     *
     * @param user the user
     * @param tokenHash the token hash
     *
     * @return the [RevokedAccessToken] if found, null otherwise
     */
    fun findByUserAndTokenHash(user: User, tokenHash: String): RevokedAccessToken?

    /**
     * Deletes an access token given a user and the token hash.
     *
     * @param user the user
     * @param tokenHash the token hash
     */
    fun deleteByUserAndTokenHash(user: User, tokenHash: String)

    /**
     * Checks if an access token exists given a user and the token hash.
     *
     * @param user the user
     * @param tokenHash the token hash
     *
     * @return true if the refresh token exists, false otherwise
     */
    fun existsByUserAndTokenHash(user: User, tokenHash: String): Boolean
}
