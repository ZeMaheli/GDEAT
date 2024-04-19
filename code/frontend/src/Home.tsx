import React, { useState, useEffect, useContext } from 'react';
import { AuthContext } from './AuthContext';
import { createRoot } from 'react-dom/client';
import {
    createBrowserRouter,
    RouterProvider,
    Link,
    useNavigate,
} from 'react-router-dom';

export function Home() {
    return (
        <div>
            <h1>"Home"</h1>
            <p>
            <Link to="/about">About</Link>{' '}
                <Link to="/login">Login</Link>{' '}
                <Link to="/register">Register</Link>{' '}
            </p>
        </div>
    );
}
