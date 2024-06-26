package com.gdeat.service.exceptions

/**
 * Exception thrown when a resource is not found.
 *
 * @param msg exception message
 */
class NotFoundException(msg: String) : Exception(msg)
