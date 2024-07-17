package server.externalAI.http.controllers.ai.models.processRequest

import server.externalAI.service.ai.models.AIRequest

data class ProcessRequestInputModel(
    val prompt: String
) {
    fun toAIRequest(): AIRequest = AIRequest(prompt = prompt)
}