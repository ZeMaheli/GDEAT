package com.gdeat.domain.diagrams.utils

import com.gdeat.domain.diagrams.utils.DiagramInformationTests.Companion.defaultDiagramInformation
import org.junit.jupiter.api.Test

class DiagramInformationConverterTests {
    @Test
    fun `DiagramInformationConverter creation is successful`() {
        DiagramInformationConverter()
    }

    @Test
    fun `DiagramInformationConverter converting DiagramInformation to DatabaseColumn is successful`() {
        val converter = DiagramInformationConverter()
        println(
            converter.convertToDatabaseColumn(
                defaultDiagramInformation
            )
        )
    }

    companion object {
        val defaultDiagramInformationConverter get() = DiagramInformationConverter()
    }

}