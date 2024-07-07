package com.gdeat.domain.diagrams.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class DiagramCreateOutputDTOConverter : AttributeConverter<DiagramInformation, String> {

    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: DiagramInformation?): String {
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): DiagramInformation {
        return objectMapper.readValue(dbData ?: "{}")
    }
}