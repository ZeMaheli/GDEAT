package com.gdeat.repository.tokens

import com.gdeat.domain.users.RefreshToken
import com.gdeat.domain.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

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

    /**
     * Counts the number of refresh tokens for a given user.
     *
     * @param user the user
     * @return the number of refresh tokens for the user
     */
    fun countByUser(user: User): Int

    /**
     * Gets a list of all refresh tokens of a certain user
     * @param user the user
     * @return list with all refresh tokens of the user
     */
    @Query("SELECT rt2 FROM RefreshToken rt2 WHERE rt2.user=:user ORDER BY rt2.expirationDate ASC")
    fun getRefreshTokensOfUser(user: User):List<RefreshToken>
}