import {RegisterOutputModel} from "./models/register/RegisterOutputModel";
import {LoginOutputModel} from "./models/login/LoginOutputModel";
import {LogoutOutputModel} from "./models/logout/LogoutOutputModel";
import {RefreshTokenOutput} from "./models/refreshToken/RefreshTokenOutputModel";
import {post} from "../../utils/fetchSiren";
import {LOGIN, LOGOUT, REFRESH_TOKEN, REGISTER} from "../../navigation/URIS";

export namespace UsersService {

    /**
     * Registers the user with the given email, username and password.
     *
     * @param email the email of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param signal the signal to cancel the request
     *
     * @return the API result of the register request
     */
    export async function register(
        email: string,
        username: string,
        password: string,
        signal?: AbortSignal
    ): Promise<RegisterOutputModel> {
        return await post(REGISTER, JSON.stringify({username, email, password}), signal)
    }

    /**
     * Logs in the user with the given username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param signal the signal to cancel the request
     *
     * @return the API result of the login request
     */
    export async function login(
        username: string,
        password: string,
        signal?: AbortSignal
    ): Promise<LoginOutputModel> {
        return await post(LOGIN, JSON.stringify({username, password}), signal)
    }

    /**
     * Logs the user out.
     *
     * @param logoutLink the link to the logout endpoint
     * @param signal the signal to cancel the request
     *
     * @return the API result of the logout request
     */
    export async function logout(
        logoutLink: string,
        signal?: AbortSignal
    ): Promise<LogoutOutputModel> {
        return await post(LOGOUT, undefined, signal)
    }

    /**
     * Refreshes the access token of the user.
     *
     * @param refreshTokenLink the link to the refresh token endpoint
     * @param signal the signal to cancel the request
     *
     * @return the API result of the refresh token request
     */
    export function refreshToken(
        refreshTokenLink: string,
        signal?: AbortSignal
    ): Promise<RefreshTokenOutput> {
        return post(REFRESH_TOKEN, undefined, signal)
    }

}
