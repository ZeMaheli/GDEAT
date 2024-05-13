package com.gdeat.service.ai

interface AIService {
    suspend fun generateText(prompt: String, maxTokens: Int): String
    suspend fun classifyText(text: String): List<String>
}