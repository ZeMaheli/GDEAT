package com.gdeat.domain.users

import com.gdeat.domain.exceptions.InvalidTokenException
import java.sql.Timestamp
import jakarta.persistence.*

/**
 * The RevokedAccessToken entity.
 *
 * @property id the id of the AccessToken
 * @property user the user that owns the refresh token
 * @property tokenHash the revoked access token
 * @property expirationDate the token's expiration date
 */
@Entity
@Table(name = "revoked_access_tokens", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "token_hash"])])
class RevokedAccessToken {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User

    @Column(name = "token_hash", nullable = false)
    val tokenHash: String

    @Column(name = "expiration_date", nullable = false)
    val expirationDate: Timestamp

    constructor(
        user: User,
        tokenHash: String,
        expirationDate: Timestamp
    ) {
        if (tokenHash.length != TOKEN_HASH_LENGTH)
            throw InvalidTokenException("The token hash must have a length of $TOKEN_HASH_LENGTH")

        this.user = user
        this.tokenHash = tokenHash
        this.expirationDate = expirationDate
    }

    companion object {
        const val TOKEN_HASH_LENGTH = 512
    }
}