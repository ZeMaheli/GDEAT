package com.gdeat.domain.users

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "refresh_tokens")
class RefreshToken {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User

    @Column(name = "token_hash", nullable = false, length = TOKEN_HASH_LENGTH)
    val tokenHash: String

    @Column(name = "expiration_date", nullable = false)
    val expirationDate: Timestamp

    constructor(
        user: User,
        tokenHash: String,
        expirationDate: Timestamp
    ) {
        if (tokenHash.length != TOKEN_HASH_LENGTH)
            TODO("Criar exceções para quando há erro na criação")

        this.user = user
        this.tokenHash = tokenHash
        this.expirationDate = expirationDate
    }

    companion object {
        const val TOKEN_HASH_LENGTH = 512
    }
}