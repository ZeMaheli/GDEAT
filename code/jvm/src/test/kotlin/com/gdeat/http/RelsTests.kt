package com.gdeat.http
import com.gdeat.http.utils.Rels.CREATE_DIAGRAM
import com.gdeat.http.utils.Rels.DELETE_DIAGRAM
import com.gdeat.http.utils.Rels.GET_DIAGRAM
import com.gdeat.http.utils.Rels.GET_DIAGRAMS
import com.gdeat.http.utils.Rels.LOGIN
import com.gdeat.http.utils.Rels.LOGOUT
import com.gdeat.http.utils.Rels.REFRESH_TOKEN
import com.gdeat.http.utils.Rels.REGISTER
import com.gdeat.http.utils.Rels.STORE_DIAGRAM
import com.gdeat.http.utils.Rels.HOME
import com.gdeat.http.utils.Rels.NEXT
import com.gdeat.http.utils.Rels.PREV
import com.gdeat.http.utils.Rels.SELF
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.net.URI

class RelsTests {
    @Test
    fun `SELF_PARAM is correct`() {
        assertEquals("self", SELF)
    }

    @Test
    fun `NEXT_PARAM is correct`() {
        assertEquals("next", NEXT)
    }

    @Test
    fun `PREV_PARAM is correct`() {
        assertEquals("previous", PREV)
    }
    @Test
    fun `HOME_PARAM is correct`() {
        assertEquals("home", HOME)
    }
    @Test
    fun `REGISTER_PARAM is correct`() {
        assertEquals("register", REGISTER)
    }
    @Test
    fun `LOGIN_PARAM is correct`() {
        assertEquals("login", LOGIN)
    }
    @Test
    fun `LOGOUT_PARAM is correct`() {
        assertEquals("logout", LOGOUT)
    }
    @Test
    fun `REFRESH_TOKEN_PARAM is correct`() {
        assertEquals("refresh-token", REFRESH_TOKEN)
    }
    @Test
    fun `CREATE_DIAGRAM_PARAM is correct`() {
        assertEquals("create-diagram", CREATE_DIAGRAM)
    }
    @Test
    fun `STORE_DIAGRAM_PARAM is correct`() {
        assertEquals("store-diagram", STORE_DIAGRAM)
    }
    @Test
    fun `DELETE_DIAGRAM_PARAM is correct`() {
        assertEquals("delete-diagram", DELETE_DIAGRAM)
    }
    @Test
    fun `GET_DIAGRAM_PARAM is correct`() {
        assertEquals("get-diagram", GET_DIAGRAM)
    }
    @Test
    fun `GET_DIAGRAMS_PARAM is correct`() {
        assertEquals("get-diagrams", GET_DIAGRAMS)
    }

}