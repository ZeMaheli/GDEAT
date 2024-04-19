package Gomoku.Utils.siren


import Gomoku.Utils.Rels
import Gomoku.Utils.Uris
import pt.isel.daw.battleships.http.media.siren.Action

/**
 * The actions of the API.
 */
object Actions {


    private const val GET_METHOD = "GET"
    private const val POST_METHOD = "POST"
    val register = Action(
        name = Rels.REGISTER,
        title = "Register",
        method = POST_METHOD,
        href = Uris.register()
    )
    val play = Action(
        name = Rels.PLAY,
        title = "Play",
        method = POST_METHOD,
        href = Uris.play()
    )

    val login = Action(
        name = Rels.LOGIN,
        title = "Login",
        method = POST_METHOD,
        href = Uris.usersLogin()
    )
    val ranking = Action(
        name = Rels.RANKING,
        title = "Ranking",
        method = GET_METHOD,
        href = Uris.ranking()
    )
    val saveGame = Action(
        name = Rels.REPLAY,
        title = "Save Game",
        method = POST_METHOD,
        href = Uris.replay()
    )

    val logout = Action(
        name = Rels.LOGOUT,
        title = "Logout",
        method = POST_METHOD,
        href = Uris.usersLogout()
    )
    val about = Action(
        name = Rels.ABOUT,
        title = "About",
        method = GET_METHOD,
        href = Uris.about()
    )
    val getUserById = Action(
        name = Rels.ABOUT,
        title = "Get User By Id",
        method = GET_METHOD,
        href = Uris.about()
    )
    val listGames = Action(
        name = Rels.LIST_GAMES,
        title = "List Games",
        method = GET_METHOD,
        href = Uris.games()
    )

    val createGame = Action(
        name = Rels.CREATE_GAME,
        title = "Create Game",
        method = POST_METHOD,
        href = Uris.games()
    )
val matchmaking = Action(
        name = Rels.MATCHMAKING,
        title = "Matchmake",
        method = POST_METHOD,
        href = Uris.gamesMatchmake()
    )


    fun joinGame(gameId: Int) = Action(
        name = Rels.JOIN_GAME,
        title = "Join Game",
        method = POST_METHOD,
        href = Uris.gameJoin(gameId)
    )



    fun getMyBoard(gameId: Int) = Action(
        name = Rels.GET_MY_BOARD,
        title = "Get My Board",
        method = GET_METHOD,
        href = Uris.myBoard(gameId)
    )

    fun leaveGame(gameId: Int) = Action(
        name = Rels.LEAVE_GAME,
        title = "Leave Game",
        method = POST_METHOD,
        href = Uris.leaveGame(gameId)
    )
}
