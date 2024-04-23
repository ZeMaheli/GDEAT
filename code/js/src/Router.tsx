import {
    createBrowserRouter, RouterProvider,
} from 'react-router-dom'
import React from 'react'
import Home from "./components/Home";
import Login from "./authentication/Login";
import Register from "./authentication/Register";
import About from "./components/About";
import UserDetails from "./components/UserDetails";
import Leaderboard from "./components/Leaderboard";
import Play from "./components/Play";
import Matchmake from "./components/Matchmake";
import Game from "./components/Game";
import {AuthRequired} from "./authentication/Session";
import Games from "./components/Games";
import GameConfiguration from "./components/GameConfiguration";

const router = createBrowserRouter([
    {
        "path": "/gomoku/game/:gameId/observe",
        "element": <Game />,
    },{
        "path": "/",
        "element": <Home />,
        "children": [
            {
                "path": "/gomoku/leaderboard",
                "element": <Leaderboard />,
            },
            {
                "path": "/gomoku/about",
                "element": <About />,
            },
            {
                "path": "/gomoku/user/:id",
                "element": <UserDetails />,
            },
            {
                "path": "/gomoku/game/{id}",
                "element": <AuthRequired><Game /></AuthRequired>,
            }
        ]
    },

    {
        "path": "/gomoku/games",
        "element": <Games />,
    },
    {
        "path": "/gomoku/game/:gameId/play",
        "element": <AuthRequired><Play /></AuthRequired>,
    },
    {
        "path": "/gomoku/login",
        "element": <Login />,
    },
    {
        "path": "/gomoku/register",
        "element": <Register />,
    },
    {
        "path": "/gomoku/configuration",
        "element": <AuthRequired><GameConfiguration /></AuthRequired>,
    },
    {
        "path": "/gomoku/matchmaking",
        "element": <AuthRequired><Matchmake /></AuthRequired>,
    },
])

export function Router() {
    return (
        <RouterProvider router={router} />
    )
}