async function fetchData(uri, method, body,signal=null) {
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

export function post(uri, body,signal=null) {
    return fetchData(uri, 'POST', body,signal)
}

export function get(uri) {
    return fetchData(uri, 'GET', undefined)
}
export function deleteMethod(uri) {
    return fetchData(uri, 'DELETE', undefined)
}
export function put(uri, body) {
    return fetchData(uri, 'POST', body)
}