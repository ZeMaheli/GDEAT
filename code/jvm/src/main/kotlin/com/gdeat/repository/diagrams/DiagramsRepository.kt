package com.gdeat.repository.diagrams

import com.gdeat.domain.diagrams.Diagram
import com.gdeat.domain.users.User
import org.springframework.data.jpa.repository.JpaRepository

interface DiagramsRepository : JpaRepository<Diagram, Long> {
    /**
     * Finds a diagram by its name and user id.
     * @param name the name of the diagram to find
     * @param user the id of the user associate with the diagram
     * @return the diagram with the given name, or null if no such diagram exists
     */
    fun findByNameAndUser(name: String, user: User): Diagram?

    /**
     * Checks if a diagram with the given name exists.
     *
     * @param name the name of the diagram to check
     * @return true if a user with the given username exists, false otherwise
     */
    fun existsByName(name: String): Boolean
}