package server.externalAI.http.controllers.ai.models.processRequest

import externalaiservice.ai.config.models.AIRequest

data class ProcessRequestInputModel(
    val prompt: String
) {
    fun toAIRequest(): AIRequest = AIRequest(prompt = prompt)
}