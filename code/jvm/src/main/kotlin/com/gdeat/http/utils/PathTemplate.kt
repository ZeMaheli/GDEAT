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

    // DIAGRAM
    const val CREATE_DIAGRAM = "/diagrams/create"
    const val STORE_DIAGRAM = "/diagrams/save"
    const val GET_DIAGRAM = "/diagrams/{name}"
    const val GET_DIAGRAMS = "/diagrams"
    const val DELETE_DIAGRAMS = "/diagrams/delete"

    // URI's
    fun home() = URI(HOME)

    fun usersLogin(): URI = URI(LOGIN)
    fun usersLogout(): URI = URI(LOGOUT)
    fun usersRefreshToken(): URI = URI(REFRESH_TOKEN)

    fun diagramCreate() = URI(CREATE_DIAGRAM)
    fun diagramsStore() = URI(STORE_DIAGRAM)
    fun diagramsGet() = URI(GET_DIAGRAMS)
    fun diagramDelete() = URI(DELETE_DIAGRAMS)
    fun diagramGet(name:String) = URI(GET_DIAGRAM.replace("{name}",name))
}