package com.gdeat.domain.exceptions

/**
 * Exception thrown when a refresh token is invalid.
 *
 * @param msg exception message
 */
class InvalidTokenException(msg: String) : Exception(msg)