import {createRoot} from "react-dom/client";
import React from "react";
import {BrowserRouter as Router} from 'react-router-dom'
import {Auth} from "./Utils/Session";
import App from "./App";

const container = document.getElementById('container');

if (!container) {
    throw new Error('Container element not found');
}

const root = createRoot(container);
root.render(
    <Router>
        <Auth>
            <App/>
        </Auth>
    </Router>
)
