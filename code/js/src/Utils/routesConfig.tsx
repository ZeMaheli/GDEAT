import React from "react";
import Home from "../components/Home";
import Login from "../components/Login";
import Register from "../components/Register";
import {Uris} from "./navigation/Uris";
import Graphs from "../components/Graphs";

const routes = [
    {path: Uris.HOME, element: <Home/>, AuthRequired: false},
    {path: Uris.LOGIN, element: <Login/>, AuthRequired: false},
    {path: Uris.REGISTER, element: <Register/>, AuthRequired: false},
    {path: Uris.GRAPHS, element: <Graphs/>, AuthRequired: true}
];

export default routes;
