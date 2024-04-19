import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { createRoot } from 'react-dom/client';

export default class App extends React.Component {
    render() {
        return (
            <View >
                <Text>Open up App.js to start working on your app!</Text>
            </View>
        );
    }
}
export function app() {
    const root = createRoot(document.getElementById('container'));
    root.render(<App />);
}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
});


/*import React, { createContext, useEffect, useState, useContext } from 'react';
import { About } from './about';
import { Logout } from './logout';
import { Register } from './register';
import { Matchmaking } from './matchMaking'
import { Board } from './Board';
import { Login } from './login';
import { Ranking } from './ranking';
import { UserHome } from './UserHome';
import { Home } from './Home';
import { SaveGame } from './saveGame';
import { CookiesProvider } from 'react-cookie';
import { AuthContext } from './AuthContext';
import { createRoot } from 'react-dom/client';
import {
    createBrowserRouter,
    RouterProvider,
    Link,
    useNavigate,
} from 'react-router-dom';

const router = createBrowserRouter([
    {
        path: '/',
        element: <Home />,
    },
    {
        path: '/me',
        element: <UserHome/>,
    },
    {
        path: '/login',
        element: <Login />,
    },
    {
        path: '/register',
        element: <Register />,
    },
    
    {
        path: '/matchmaking',
        element: <Matchmaking />
    },
    {
        path : '/ranking',
        element: <Ranking />
    },
    { path: '/board',
        element: <Board />},
     {
            path: '/logout',
            element: <Logout />,
        },
        {
            path: '/about',
            element: <About />,
        },
        {
            path: '/saveGame',
            element: <SaveGame />,
        }
        
    
]);


export function App() {
    const [loggedin, setLoggedin] = useState(false);
    const [id, setId] = useState(0);
    const [token, setToken] = useState("");
    const [gameID, setGameID] = useState(0);

    
    return (
    <CookiesProvider defaultSetOptions={{ path: '/' }}>
        <AuthContext.Provider value={{ loggedin, setLoggedin, id, setId, token, setToken, gameID, setGameID }}>
            <RouterProvider router={router} />
        </AuthContext.Provider>
    </CookiesProvider>
    )
}
 export function app() {
    const root = createRoot(document.getElementById('container'));
    root.render(<App /> );
        
}
*/