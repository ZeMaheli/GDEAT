package Gomoku.Utils.siren



import Gomoku.Utils.Rels
import Gomoku.Utils.Uris
import pt.isel.daw.battleships.http.media.siren.Link
import pt.isel.daw.battleships.http.media.siren.SubEntity
import java.net.URI

/**
 * The links of the API.
 */
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
    val rankings = Link(
        rel = listOf(Rels.RANKING),
        href = Uris.ranking()
    )

    val home = Link(
        rel = listOf(Rels.HOME),
        href = Uris.home()
    )


    val userHome = Link(
        rel = listOf(Rels.USER_HOME),
        href = Uris.userHome()
    )
    val register = Link(
        rel = listOf(Rels.REGISTER),
        href = Uris.register()
    )
    val login = Link(
        rel = listOf(Rels.LOGIN),
        href = Uris.usersLogin()
    )
    val logout = Link(
        rel = listOf(Rels.LOGOUT),
        href = Uris.usersLogout()
    )

    val play = Link(
        rel = listOf(Rels.PLAY),
        href = Uris.play()
    )
    val replay = Link(
        rel = listOf(Rels.REPLAY),
        href = Uris.replay()
    )
    val about = Link(
        rel = listOf(Rels.ABOUT),
        href = Uris.about()
    )


    /**
     * Creates a link to the game sub-entity.
     *
     * @param gameId the game's id
     * @return the link
     */
    fun game(gameId: Int) = SubEntity.EmbeddedLink(
        rel = listOf(Rels.GAME),
        href = Uris.gameById(gameId = gameId)
    )
}
