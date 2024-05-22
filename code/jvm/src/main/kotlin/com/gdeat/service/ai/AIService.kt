package com.gdeat.service.ai

interface AIService<RequestObject, ResponseObject> {
    suspend fun generateEntitiesAndRelations(request: RequestObject): ResponseObject
}