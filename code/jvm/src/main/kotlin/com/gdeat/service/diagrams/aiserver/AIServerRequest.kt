package com.gdeat.service.diagrams.aiserver

/**
 * A class to be used to request from the AI server
 *
 * @property prompt The prompt to send the AI server
 */
data class AIServerRequest(val prompt: String)