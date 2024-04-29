package com.gdeat.http.controllers.graphs

import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.io.ByteArrayOutputStream

@Controller
class GraphController {

    @GetMapping("/graph", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun renderGraph(): ResponseEntity<ByteArray> {
        // Create a DOT graph description
        val dotGraph = """
            digraph G {
                A -> B
                B -> C
                C -> A
            }
        """.trimIndent()

        // Use Graphviz to render the graph
        val renderedGraph = Graphviz.fromString(dotGraph).render(Format.PNG)

        // Convert the rendered graph to a byte array
        val outputStream = ByteArrayOutputStream()
        renderedGraph.toOutputStream(outputStream)
        val graphByteArray = outputStream.toByteArray()

        // Return the graph as a PNG image
        return ResponseEntity.ok().body(graphByteArray)
    }
}