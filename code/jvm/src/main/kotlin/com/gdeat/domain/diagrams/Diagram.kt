package com.gdeat.domain.diagrams

import com.gdeat.domain.diagrams.utils.DiagramCreateOutputDTOConverter
import com.gdeat.domain.diagrams.utils.DiagramInformation
import com.gdeat.domain.exceptions.InvalidDiagramException
import com.gdeat.domain.exceptions.InvalidUserException
import com.gdeat.domain.users.User
import jakarta.persistence.*


@Entity
@Table(name = "diagrams", uniqueConstraints = [UniqueConstraint(columnNames = ["name", "user"])])
class Diagram {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User

    @Column(name = "name", nullable = false, length = MAX_NAME_LENGTH)
    val name: String

    @Column(name = "name", nullable = false, length = MAX_NAME_LENGTH)
    val prompt: String

    @Column(name = "data", nullable = false, columnDefinition = "jsonb")
    @Convert(converter = DiagramCreateOutputDTOConverter::class)
    val data: DiagramInformation

    constructor(
        name: String,
        user: User,
        prompt: String,
        data: DiagramInformation
    ) {

        if (name.length !in MIN_NAME_LENGTH..MAX_NAME_LENGTH) {
            throw InvalidUserException("Invalid username length. Please provide username length between $MIN_NAME_LENGTH..$MAX_NAME_LENGTH.")
        }

        if (prompt.isBlank()) {
            throw InvalidDiagramException("Please provide a non empty prompt.")
        }

        if (data.Entities.isEmpty() || data.Relations.isEmpty()) {
            throw InvalidDiagramException("Please provide a non empty data object.")
        }

        this.name = name
        this.user = user
        this.prompt = prompt
        this.data = data
    }

    companion object {
        private const val MIN_NAME_LENGTH = 3
        private const val MAX_NAME_LENGTH = 64
    }
}
