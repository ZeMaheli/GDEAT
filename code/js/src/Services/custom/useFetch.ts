async function fetchData(uri: string, method: string, body?: string, signal=null) {
    try {
        const options: RequestInit = {
            credentials: 'include',
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: body,
            signal:signal
        }
        const response = await fetch(uri, options)
        const data = await response.json()
        if (!response.ok) {
            throw new Error(data.error);
        }

        return data;
    } catch (error) {
        throw error
    }
}

export function post(uri: string, body?: string, signal=null) {
    return fetchData(uri, 'POST', body,signal)
}

export function get(uri: string) {
    return fetchData(uri, 'GET', undefined)
}
export function deleteMethod(uri: string) {
    return fetchData(uri, 'DELETE', undefined)
}
export function put(uri: string, body: string) {
    return fetchData(uri, 'POST', body)
}