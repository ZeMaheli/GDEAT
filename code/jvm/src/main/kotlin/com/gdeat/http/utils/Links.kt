package com.gdeat.http.utils

import sirenentity.siren.Link
import sirenentity.siren.SubEntity
import java.net.URI
import kotlin.io.path.Path

object Links {
    /**
     * Creates a self link.
     *
     * @param href the link's href
     * @return the link
     */
    fun self(href: URI) = Link(
        rel = listOf(Rels.SELF),
        href = href
    )

    val home = Link(
        rel = listOf(Rels.HOME),
        href = PathTemplate.home()
    )

    /**
     * Creates a link to the game sub-entity.
     *
     * @param gameId the game's id
     * @return the link
     */
    fun diagram(diagramName: String) = SubEntity.EmbeddedLink(
        rel = listOf(Rels.DIAGRAM),
        href = PathTemplate.diagramGet(name = diagramName)
    )
}