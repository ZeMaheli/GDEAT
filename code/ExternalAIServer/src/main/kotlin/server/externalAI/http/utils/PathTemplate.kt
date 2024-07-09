package server.externalAI.http.utils

import java.net.URI

object PathTemplate {
    const val PROCESS_REQUEST = "/process"

    fun processRequest() = URI(PROCESS_REQUEST)
}