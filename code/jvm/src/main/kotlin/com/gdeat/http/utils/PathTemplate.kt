package com.gdeat.http.utils

import java.net.URI

object PathTemplate {

    const val ABOUT = "/about"
    const val HOME = "/"

    // USER
    const val REGISTER = "/users/register"
    const val LOGIN = "/users/login"
    const val LOGOUT = "/users/logout"
    const val REFRESH_TOKEN = "/users/refresh-token"

    // GRAPH
    const val CREATE = "/graphs/create"
    const val EDIT = "/graphs/edit"
    const val DELETE = "/graphs/delete"

    // URI's
    fun home() = URI(HOME)

    fun usersLogin(): URI = URI(LOGIN)
    fun usersLogout(): URI = URI(LOGOUT)
    fun usersRefreshToken(): URI = URI(REFRESH_TOKEN)
}