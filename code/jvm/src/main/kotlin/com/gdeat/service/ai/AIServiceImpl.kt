package com.gdeat.service.ai

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class AIServiceImpl: AIService {
    override suspend fun generateText(prompt: String, maxTokens: Int): String {
        TODO("Not yet implemented")
    }

    override suspend fun classifyText(text: String): List<String> {
        TODO("Not yet implemented")
    }
}