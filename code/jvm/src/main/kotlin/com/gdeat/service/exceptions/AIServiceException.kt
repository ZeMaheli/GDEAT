package com.gdeat.service.exceptions

/**
 * Exception thrown when there's an error requesting to LLM.
 *
 * @param msg exception message
 */
class AIServiceException (msg: String): Exception(msg)