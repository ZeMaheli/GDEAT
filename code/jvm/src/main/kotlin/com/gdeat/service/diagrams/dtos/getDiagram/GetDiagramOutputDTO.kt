package com.gdeat.service.diagrams.dtos.getDiagram

/**
 * A Diagram Information DTO.
 *
 * @property diagramCode code that generates the graph
 * @property diagramPDF visualization generated for the graph
 */
data class GetDiagramOutputDTO(
    val diagramCode: String,
    val diagramPDF: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetDiagramOutputDTO

        if (diagramCode != other.diagramCode) return false
        if (!diagramPDF.contentEquals(other.diagramPDF)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = diagramCode.hashCode()
        result = 31 * result + diagramPDF.contentHashCode()
        return result
    }
}