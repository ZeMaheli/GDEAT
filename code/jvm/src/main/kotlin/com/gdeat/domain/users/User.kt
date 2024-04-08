package com.gdeat.domain.users

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0

    @Column(name = "username", nullable = false, unique = true, length = MAX_USERNAME_LENGTH)
    val username: String

    @Column(name = "password_hash", nullable = false, length = PASSWORD_HASH_LENGTH)
    val passwordHash: String

    @Column(name = "email", nullable = false, unique = true, length = 320)
    val email: String

    constructor(
        username: String,
        passwordHash: String,
        email: String
    ){

        if(username.length < MIN_USERNAME_LENGTH || username.length > MAX_USERNAME_LENGTH)
            TODO("Criar exceções para quando há erro na criação")

        if (passwordHash.length != PASSWORD_HASH_LENGTH)
            TODO("Criar exceções para quando há erro na criação")

        this.username = username
        this.passwordHash = passwordHash
        this.email = email
    }

    companion object{
        private const val MIN_USERNAME_LENGTH = 3
        private const val MAX_USERNAME_LENGTH = 64
        private const val PASSWORD_HASH_LENGTH = 256
    }
}
