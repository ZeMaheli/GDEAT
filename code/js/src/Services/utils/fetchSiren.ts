import {SirenEntity, sirenMediaType} from "../media/siren/SirenEntity"
import {JsonError, jsonErrorMediaType} from "../media/error/JsonError"
import * as React from "react"
import to from "../../Utils/await-to"
import {NavigateFunction} from "react-router-dom"

/**
 * Fetches a Siren entity from the given URL.
 *
 * @param input the URL to fetch the Siren entity from
 * @param method the HTTP method to use
 * @param body the body to send with the request
 * @param signal the signal to abort the request
 *
 * @return a promise that resolves to the Siren entity
 */
export async function fetchSiren<T>(
    input: RequestInfo | URL,
    method?: string,
    body?: BodyInit,
    signal?: AbortSignal
): Promise<SirenEntity<T>> {

    const headers: any = {
        'Accept': `${sirenMediaType}, ${jsonErrorMediaType}`,
        'Content-Type': 'application/json',
    }

    const [err, res] = await to(fetch(input, {
        method: method,
        headers,
        body: body,
        signal: signal,
        credentials: 'include'
    }))

    if (err)
        throw new NetworkError(err.message)

    if (!res.ok) {
        if (res.headers.get('Content-Type') !== jsonErrorMediaType)
            throw new UnexpectedResponseError(`Unexpected response type: ${res.headers.get('Content-Type')}`)

        throw new JsonError(await res.json())
    }

    if (res.headers.get('Content-Type') !== sirenMediaType)
        throw new UnexpectedResponseError(`Unexpected response type: ${res.headers.get('Content-Type')}`)

    const json = await res.json()

    return new SirenEntity<T>(json)
}

/**
 * Handles an API error response.
 *
 * @param err the error
 * @param setError the error setter
 * @param navigate the navigate function
 */
export function handleError(
    err: Error | JsonError,
    setError: React.Dispatch<React.SetStateAction<string | null>>,
    navigate?: NavigateFunction
) {
    if (navigate != undefined) {
        navigate("/login")
    } else if (err instanceof JsonError)
        setError(err.message)
    else
        setError(err.message)
}

/**
 * Sends a GET request to the specified link.
 *
 * @param input the link to send the request to
 * @param signal the signal to abort the request
 *
 * @return the result of the request
 */
export function get<T>(input: RequestInfo | URL, signal?: AbortSignal): Promise<SirenEntity<T>> {
    return fetchSiren<T>(input, undefined, undefined, signal)
}

/**
 * Sends a POST request to the specified link with the specified body and a token in the header.
 *
 * @param link the link to send the request to
 * @param body the body to send in the request, if undefined, an empty request is sent
 * @param signal the signal to abort the request
 *
 * @return the result of the request
 */
export function post<T>(
    link: RequestInfo | URL,
    body?: BodyInit,
    signal?: AbortSignal
): Promise<SirenEntity<T>> {
    return fetchSiren<T>(link, 'POST', body, signal)
}

/**
 * An error that occurs if there is a network error.
 */
export class NetworkError extends Error {
    constructor(message: string) {
        super(message)
    }
}

/**
 * An error that occurs if the response is not a Siren entity.
 */
export class UnexpectedResponseError extends Error {
    constructor(message: string) {
        super(message)
    }
}
