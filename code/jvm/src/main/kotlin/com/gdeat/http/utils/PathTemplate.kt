package com.gdeat.http.utils

import java.net.URI

object PathTemplate {

    const val ABOUT = "/about"
    private const val HOME = "/"

    // USER
    const val REGISTER = "/users/register"
    const val LOGIN = "/users/login"
    const val LOGOUT = "/users/logout"
    const val REFRESH_TOKEN = "/users/refresh-token"

    // GRAPH
    const val CREATE_DIAGRAM = "/graphs/create"
    const val STORE_DIAGRAM = "/graphs/save"
    const val GET_DIAGRAM = "/graphs/{id}"
    const val GET_DIAGRAMS = "/graphs"
    const val DELETE_DIAGRAMS = "/graphs/{id}/delete"

    // URI's
    fun home() = URI(HOME)

    fun usersLogin(): URI = URI(LOGIN)
    fun usersLogout(): URI = URI(LOGOUT)
    fun usersRefreshToken(): URI = URI(REFRESH_TOKEN)

    fun diagramCreate() = URI(CREATE_DIAGRAM)
    fun diagramsStore() = URI(STORE_DIAGRAM)
    fun diagramsGet() = URI(GET_DIAGRAMS)
    fun diagramDelete() = URI(DELETE_DIAGRAMS)
    fun diagramGet() = URI(GET_DIAGRAM)
}