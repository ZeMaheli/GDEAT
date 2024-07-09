package sirenentity.siren

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

/**
 * Siren is a specification for representing hypermedia entities in JSON.
 *
 * @see <a href="https://github.com/kevinswiber/siren">Siren Specification</a>
 *
 * @property class the class of the entity (optional)
 * @property properties the properties of the entity (optional)
 * @property entities the sub-entities of the entity (optional)
 * @property actions the actions that can be performed on the entity (optional)
 * @property links the links to other entities (optional)
 * @property title the title of the entity (optional)
 */
data class SirenEntity<T>(
    val `class`: List<String>? = null,
    val properties: T? = null,
    val entities: List<SubEntity>? = null,
    val actions: List<Action>? = null,
    val links: List<Link>? = null,
    val title: String? = null
) {
    /**
     * Transforms the current SirenEntity class into a response entity with class as body
     *
     * @param status the http status code of the response entity
     * @return Response entity with status code and SirenEntity as body, with content type application/vnd.siren+json
     */
    fun toResponse(status: HttpStatus): ResponseEntity<SirenEntity<T>> =
        ResponseEntity.status(status).contentType(sirenMediaType).body(this)

    companion object {
        val sirenMediaType = MediaType("application","vnd.siren+json")
    }
}
