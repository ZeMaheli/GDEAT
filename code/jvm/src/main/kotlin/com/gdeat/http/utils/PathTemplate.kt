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
    const val CREATE_GRAPH = "/graphs/create"
    const val GET_GRAPH = "/graphs/{id}"
    const val EDIT_GRAPH = "/graphs/{id}/edit"
    const val DELETE_GRAPH = "/graphs/{id}/delete"

    // URI's
    fun home() = URI(HOME)

    fun usersLogin(): URI = URI(LOGIN)
    fun usersLogout(): URI = URI(LOGOUT)
    fun usersRefreshToken(): URI = URI(REFRESH_TOKEN)
}