package com.gdeat.http

import com.gdeat.http.utils.PathTemplate
import com.gdeat.http.utils.PathTemplate.ABOUT
import com.gdeat.http.utils.PathTemplate.CREATE_DIAGRAM
import com.gdeat.http.utils.PathTemplate.DELETE_DIAGRAMS
import com.gdeat.http.utils.PathTemplate.GET_DIAGRAM
import com.gdeat.http.utils.PathTemplate.GET_DIAGRAMS
import com.gdeat.http.utils.PathTemplate.LOGIN
import com.gdeat.http.utils.PathTemplate.LOGOUT
import com.gdeat.http.utils.PathTemplate.REFRESH_TOKEN
import com.gdeat.http.utils.PathTemplate.REGISTER
import com.gdeat.http.utils.PathTemplate.STORE_DIAGRAM
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.net.URI

class ParamsTests {

    @Test
    fun `ABOUT_PARAM is correct`() {
        assertEquals("/about", ABOUT)
    }

    @Test
    fun `REGISTER_PARAM is correct`() {
        assertEquals("/users/register", REGISTER)
    }

    @Test
    fun `LOGIN_PARAM is correct`() {
        assertEquals("/users/login", LOGIN)
    }
    @Test
    fun `LOGOUT_PARAM is correct`() {
        assertEquals("/users/logout", LOGOUT)
    }
    @Test
    fun `REFRESH_TOKEN_PARAM is correct`() {
        assertEquals("/users/refresh-token", REFRESH_TOKEN)
    }
    @Test
    fun `CREATE_DIAGRAM_PARAM is correct`() {
        assertEquals("/graphs/create", CREATE_DIAGRAM)
    }
    @Test
    fun `STORE_DIAGRAM_PARAM is correct`() {
        assertEquals("/graphs/save", STORE_DIAGRAM)
    }
    @Test
    fun `GET_DIAGRAM_PARAM is correct`() {
        assertEquals("/graphs/{name}", GET_DIAGRAM)
    }
    @Test
    fun `GET_DIAGRAMS_PARAM is correct`() {
        assertEquals("/graphs", GET_DIAGRAMS)
    }
    @Test
    fun `DELETE_DIAGRAMS_PARAM is correct`() {
        assertEquals("/graphs/{name}/delete", DELETE_DIAGRAMS)
    }


    @Test
    fun `HOME_URI is correct`() {
        assertEquals(URI("/"), PathTemplate.home())
    }
    @Test
    fun `LOGIN_URI is correct`() {
        assertEquals(URI(LOGIN), PathTemplate.usersLogin())
    }
    @Test
    fun `LOGOUT_URI is correct`() {
        assertEquals(URI(LOGOUT), PathTemplate.usersLogout())
    }
    @Test
    fun `REFRESH_TOKEN_URI is correct`() {
        assertEquals(URI(REFRESH_TOKEN), PathTemplate.usersRefreshToken())
    }
    @Test
    fun `CREATE_DIAGRAM_URI is correct`() {
        assertEquals(URI(CREATE_DIAGRAM), PathTemplate.diagramCreate())
    }
    @Test
    fun `DELETE_DIAGRAMS_URI is correct`() {
        assertEquals(DELETE_DIAGRAMS.replace("{name}",name), PathTemplate.diagramDelete(name).path)
    }
    @Test
    fun `GET_DIAGRAM_URI is correct`() {
        assertEquals(GET_DIAGRAM.replace("{name}",name), PathTemplate.diagramGet(name).path)
    }
    @Test
    fun `GET_DIAGRAMS_URI is correct`() {
        assertEquals(URI(GET_DIAGRAMS), PathTemplate.diagramsGet())
    }
    @Test
    fun `STORE_DIAGRAM_URI is correct`() {
        assertEquals(URI(STORE_DIAGRAM), PathTemplate.diagramsStore())
    }

companion object{
    val name get() = "diagram1"
}

}
