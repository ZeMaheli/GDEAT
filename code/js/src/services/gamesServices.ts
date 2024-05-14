import { deleteMethod, get, post, put } from "./custom/useFetch";
import { CREATE, EDIT, DELETE } from "./navigation/URIS";
import { refreshToken } from "./usersServices";

export async function executeRequestAndRefreshToken(requestFunction, ...args) {
    try {
        const response = await requestFunction(...args);

        if (response.status === 401) {
            // Token expired, refresh it and retry the request
            await refreshToken();
            return requestFunction(...args);
        }

        return response;
    } catch (error) {
        throw error;
    }
}
export async function createGraph(start, max) {
    return await executeRequestAndRefreshToken(
        post,
        CREATE + '?start=' + start + '&max=' + max
    );
}
export async function editGraph(start, max) {
    return await executeRequestAndRefreshToken(
        put,
        EDIT + '?start=' + start + '&max=' + max
    );
}
export async function deleteGraph(start, max) {
    return await executeRequestAndRefreshToken(
        deleteMethod,
        DELETE
    );
}
/*
export async function getGames(start, max){
    return await executeRequestAndRefreshToken(
        get,
        GAMES + '?start=' + start + '&max=' + max
    );
}
export async function matchmake(userId, boardSize, variation) {
    return await executeRequestAndRefreshToken(
        post,
        MATCHMAKING,
        JSON.stringify({ userId, boardSize, variation })
    );
}


export async function pollLobby(lobbyId, userId){
    return await executeRequestAndRefreshToken(
        post,
        LOBBY + '/' + lobbyId,
        JSON.stringify({userId})
    )
}

export async function leaveLobby(lobbyId, userId){
    return await executeRequestAndRefreshToken(
        post,
        LOBBY + '/' + lobbyId + '/leave',
        JSON.stringify({userId})
    )
}

export async function pollGame(gameId){
    return await executeRequestAndRefreshToken(
        get,
        GAME + '/' + gameId + '/observe'
    );
}

export async function leaveGame(gameId, userId){
    return await executeRequestAndRefreshToken(
        post,
        GAME + '/' + gameId + '/leave',
        JSON.stringify({userId})
    )
}

export async function play(gameId, userId, line, column){
    return await executeRequestAndRefreshToken(
        post,
        GAME + '/' + gameId + '/play',
        JSON.stringify({userId, line, column})
    )
}*/