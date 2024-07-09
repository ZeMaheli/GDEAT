package sirenentity.error

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

/**
 * Specification for representing hypermedia entities in JSON.
 *
 * @see <a href="https://www.iana.org/assignments/media-types/application/json">Json Specification</a>
 *
 * @property code the http status code of the error
 * @property message the message of the error
 * @property details detailed message of the error
 */
data class JsonError(
    val code: Int,
    val message: String,
    val details: String?,
) {
    /**
     * Transforms the current JsonError class into a response entity with class as body
     *
     * @param status the http status code of the response entity
     * @return Response entity with status code and JsonError as body, with content type application/json
     */
    fun toResponse(status: HttpStatus): ResponseEntity<JsonError> =
        ResponseEntity.status(status).contentType(errorMediaType).body(this)

    companion object {
        val errorMediaType = MediaType("application","json")
    }
}