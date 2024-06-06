package com.gdeat.service.diagrams

import com.gdeat.service.ai.AIServiceImpl
import com.gdeat.service.ai.config.models.AIRequest
import com.gdeat.service.ai.config.models.EntityRelationDiagramInfo
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateInputDTO
import com.gdeat.service.diagrams.dtos.createDiagram.DiagramCreateOutputDTO
import com.gdeat.service.diagrams.dtos.deleteDiagram.DeleteDiagramOutputDTO
import com.gdeat.service.diagrams.dtos.getDiagram.GetDiagramOutputDTO
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.ByteArrayOutputStream

@Service
@Transactional(rollbackFor = [Exception::class])
class DiagramsServiceImpl(private val aiService: AIServiceImpl) : DiagramsService {

    override suspend fun createGraph(diagramCreateInputDTO: DiagramCreateInputDTO): DiagramCreateOutputDTO {
        val diagramInfo = aiService.generateEntitiesAndRelations(AIRequest.toAIRequest(diagramCreateInputDTO))
        val neatoCode = withContext(Dispatchers.IO) {
            createNeatoCode(diagramInfo)
        }
        val diagramPDF = createNeatoDiagram(neatoCode)
        return DiagramCreateOutputDTO(neatoCode, diagramPDF)
    }

    fun createNeatoCode(diagramInfo: EntityRelationDiagramInfo): String {
        val stringBuilder = StringBuilder()
        stringBuilder.appendLine("graph ER {")
        stringBuilder.appendLine("\tfontname=\"Helvetica,Arial,sans-serif\"")
        stringBuilder.appendLine("\tnode [fontname=\"Helvetica,Arial,sans-serif\"]")
        stringBuilder.appendLine("\tedge [fontname=\"Helvetica,Arial,sans-serif\"]")
        stringBuilder.appendLine("\tlayout=neato")

        // Define entity nodes
        stringBuilder.appendLine("\tnode [shape=box];")
        diagramInfo.Entities.keys.forEach { entity ->
            stringBuilder.appendLine("\t$entity;")
        }

        // Define attribute nodes and their connections
        diagramInfo.Entities.forEach { (entity, attributes) ->
            attributes.forEachIndexed { index, attribute ->
                val attributeName = "${entity}_attr$index"
                stringBuilder.appendLine("\tnode [shape=ellipse, label=\"$attribute\"] $attributeName;")
                stringBuilder.appendLine("\t$entity -- $attributeName;")
            }
        }

        // Define relationships between entities
        diagramInfo.Relations.forEach { (entity1, relationMap) ->
            relationMap.forEach { (entity2, relation) ->
                stringBuilder.appendLine("\t$entity1 -- $entity2 [label=\"$relation\"];")
            }
        }

        stringBuilder.appendLine("\tlabel = \"Entity Relation Diagram\"")
        stringBuilder.appendLine("\tfontsize=20")
        stringBuilder.appendLine("}")

        return stringBuilder.toString()
    }

    private fun createNeatoDiagram(neatoCode: String): ByteArray {
        val outputStream = ByteArrayOutputStream()
        Graphviz.fromString(neatoCode).render(Format.SVG).toOutputStream(outputStream)
        return outputStream.toByteArray()
    }

    override fun getGraph(): GetDiagramOutputDTO {
        TODO("Not yet implemented")
    }

    override fun editGraph() {
        TODO("Not yet implemented")
    }

    override fun deleteGraph(): DeleteDiagramOutputDTO {
        TODO("Not yet implemented")
    }
}