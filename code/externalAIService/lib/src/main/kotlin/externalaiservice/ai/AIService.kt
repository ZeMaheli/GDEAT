package externalaiservice.ai

/**
 * A service interface for generating entities and relations using an AI model.
 *
 * @param RequestObject The type of the request object.
 * @param ResponseObject The type of the response object.
 */
interface AIService<RequestObject, ResponseObject> {
    /**
     * Generates a response from a model with the entities and relations based on the provided request.
     *
     * This function is a suspend function, meaning it can be called from a coroutine
     * or another suspend function. It performs the generation of entities and relations
     * asynchronously.
     *
     * @param request The request object containing the input data for generating entities and relations.
     * @return The response object containing the generated entities and relations.
     */
    suspend fun generateEntitiesAndRelations(request: RequestObject): ResponseObject
}