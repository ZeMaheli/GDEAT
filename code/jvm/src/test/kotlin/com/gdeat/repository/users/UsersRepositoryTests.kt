package com.gdeat.repository.users

import com.gdeat.domain.users.User
import com.gdeat.testUtils.DatabaseTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UsersRepositoryTests(
    @Autowired
    val usersRepository: UsersRepository
) : DatabaseTest() {

    @Test
    fun `findByUsername returns the user with the given username`() {
        val user = entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        val foundUser = usersRepository.findByUsername("bob")

        assertNotNull(foundUser)
        assertEquals(user, foundUser)
    }

    @Test
    fun `findByUsername returns null if a user with the given username is not found`() {
        val foundUser = usersRepository.findByUsername("bob")

        assertNull(foundUser)
    }

    @Test
    fun `existsByUsername returns true if a user with the given username exists`() {
        entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        assertTrue(usersRepository.existsByUsername("bob"))
    }

    @Test
    fun `existsByUsername returns false if a user with the given username does not exist`() {
        assertFalse(usersRepository.existsByUsername("bob"))
    }

    @Test
    fun `existsByEmail returns true if a user with the given email exists`() {
        entityManager.persist(
            User(
                username = "bob",
                email = "bob@bob.com",
                passwordHash = "a"
            )
        )

        assertTrue(usersRepository.existsByEmail("bob@bob.com"))
    }

    @Test
    fun `existsByEmail returns false if a user with the given email does not exist`() {
        assertFalse(usersRepository.existsByEmail("bob@bob.com"))
    }
}
