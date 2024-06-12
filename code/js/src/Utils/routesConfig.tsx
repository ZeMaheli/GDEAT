import React from "react";
import Home from "../components/Home";
import Login from "../authentication/Login";
import Register from "../authentication/Register";
import {Uris} from "./navigation/Uris";
import Graphs from "../components/Graph";

const routes = [
    {path: Uris.HOME, element: <Home/>},
    {path: Uris.LOGIN, element: <Login/>},
    {path: Uris.REGISTER, element: <Register/>},
    {path: Uris.GRAPHS, element: <Graphs/>}
];

export default routes;
