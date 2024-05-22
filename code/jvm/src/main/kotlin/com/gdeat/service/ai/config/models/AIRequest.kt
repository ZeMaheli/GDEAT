package com.gdeat.service.ai.config.models

data class AIRequest(
    val model: String = "llama3",
    val prompt: String,
    val stream: Boolean = false,
)