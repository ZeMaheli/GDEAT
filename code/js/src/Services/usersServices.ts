import { get, post } from "./custom/useFetch";
import { ABOUT, GET_ID_BY_TOKEN, GET_USER_BY_ID, LOGIN, LOGOUT, REFRESH_TOKEN, REGISTER } from "./navigation/URIS";

export async function about() {
    return await get(ABOUT)
}

export async function register(name: string, email: string, password: string) {
    return await post(REGISTER, JSON.stringify({ name, email, password }))
}

export async function login(email: string, password: string) {
    return await post(LOGIN, JSON.stringify({ email, password }))
}

export async function logout() {
    return await post(LOGOUT, undefined)
}

export async function getUserById(userId: string) {
    return await get(GET_USER_BY_ID + userId)
}

export async function getIdByToken() {
    return await get(GET_ID_BY_TOKEN)
}

export async function refreshToken() {
    return await post(REFRESH_TOKEN, undefined)
}
