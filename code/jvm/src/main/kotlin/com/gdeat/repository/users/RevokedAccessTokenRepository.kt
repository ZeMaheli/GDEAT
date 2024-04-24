package com.gdeat.repository.users

import com.gdeat.domain.users.RevokedAccessToken
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository for the [RevokedAccessToken] entity.
 */
interface RevokedAccessTokensRepository : JpaRepository<RevokedAccessToken, Long> {
}
