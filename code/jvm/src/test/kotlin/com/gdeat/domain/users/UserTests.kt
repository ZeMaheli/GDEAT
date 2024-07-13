package com.gdeat.domain.users

import com.gdeat.domain.exceptions.InvalidUserException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

class UserTests {

    @Test
    fun `User creation is successful`() {

        val user = User(
            username = "User1",
            email = "user1@email.com",
            passwordHash = "a"
        )

        val userId = User::class.declaredMemberProperties
            .first { it.name == "id" }.also { it.isAccessible = true }
            .call(user) as Long?

        assertEquals(0,userId)
        assertEquals("User1", user.username)
        assertEquals("user1@email.com", user.email)
        assertEquals("a", user.passwordHash)
    }

    @Test
    fun `User creation throws InvalidUserException if username is shorter than minimum username length`() {
        assertThrows<InvalidUserException> {
            User(
                username = "a".repeat(2),
                email = "user1@email.com",
                passwordHash = "a"
            )
        }
    }

    @Test
    fun `User creation throws InvalidUserException if username is longer than maximum username length`() {
        assertThrows<InvalidUserException> {
            User(
                username = "a".repeat(65),
                email = "user1@email.com",
                passwordHash = "a"
            )
        }
    }

    @Test
    fun `User creation throws InvalidUserException if email is not a valid email address`() {
        assertThrows<InvalidUserException> {
            User(
                username = "User1",
                email = "a".repeat(129),
                passwordHash = "a"
            )
        }
    }

    @Test
    fun `User creation throws InvalidUserException if password hash length is invalid`() {
        assertThrows<InvalidUserException> {
            User(
                username = "User1",
                email = "user1@email.com",
                passwordHash = "a".repeat(257)
            )
        }
    }

    companion object{
        val defaultUser get() = User(
            username = "User1",
            email = "user1@email.com",
            passwordHash = "a"
        )
    }
}
