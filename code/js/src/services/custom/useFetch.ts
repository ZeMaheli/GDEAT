async function fetchData(uri, method, body) {
    try {
        const options: RequestInit = {
            credentials: 'include',
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: body
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

export function post(uri, body) {
    return fetchData(uri, 'POST', body)
}

export function get(uri){
    return fetchData(uri, 'GET', undefined)
}