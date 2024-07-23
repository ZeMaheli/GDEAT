import React from "react";
import Home from "../components/Home/Home";
import Login from "../components/Authentication/Login";
import Register from "../components/Authentication/Register";
import {Uris} from "./navigation/Uris";
import Diagrams from "../components/Diagrams/Diagrams";
import Diagram from "../components/Diagram/Diagram";

const routes = [
    {path: Uris.HOME, element: <Home/>, AuthRequired: false},
    {path: Uris.LOGIN, element: <Login/>, AuthRequired: false},
    {path: Uris.REGISTER, element: <Register/>, AuthRequired: false},
    {path: Uris.DIAGRAMS, element: <Diagrams/>, AuthRequired: true},
    {path: Uris.DIAGRAM, element: <Diagram/>, AuthRequired: true}
];

export default routes;
