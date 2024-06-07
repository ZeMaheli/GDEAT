import {createBrowserRouter, RouterProvider,} from 'react-router-dom'
import React from 'react'
import Home from "./components/Home";
import Login from "./authentication/Login";
import Register from "./authentication/Register";
import About from "./components/About";
import {AuthRequired} from "./authentication/Session";
import Graph from "./components/Graph"


const router = createBrowserRouter([
    {
        "path": "/",
        "element": <Home/>,
        "children": [
            {
                "path": "/about",
                "element": <About/>,
            },
        ]
    },
    {
        "path": "/gomoku/user/:id",
        "element": <AuthRequired><Graph/></AuthRequired>,
    },
    {
        "path": "/users/login",
        "element": <Login/>,
    },
    {
        "path": "/users/register",
        "element": <Register/>,
    },

])

export function Router() {
    return (
        <RouterProvider router={router}/>
    )
}