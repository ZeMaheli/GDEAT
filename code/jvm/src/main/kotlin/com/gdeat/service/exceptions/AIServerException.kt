package com.gdeat.service.exceptions

/**
 * Exception thrown when there's an error communicating with AI Server
 *
 * @param msg exception message
 */
class AIServerException(msg: String) : Exception(msg)