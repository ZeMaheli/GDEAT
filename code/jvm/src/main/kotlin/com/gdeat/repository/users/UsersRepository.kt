package com.gdeat.repository.users

import com.gdeat.domain.users.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository for the [User] entity
 */
interface UsersRepository : JpaRepository<User, Long> {

    /**
     * Finds a user by its username.
     * @param username the username of the user to find
     * @return the user with the given username, or null if no such user exists
     */
    fun findByUsername(username: String): User?

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username of the user to check
     * @return true if a user with the given username exists, false otherwise
     */
    fun existsByUsername(username: String): Boolean
}