import React from "react";
import { Navigate } from "react-router-dom";

export function getSessionToken(): string | null {
    return localStorage.getItem('accessToken');
}

export function isLogged(): boolean {
    const token = localStorage.getItem('accessToken');
    return token !== null;
}

export function clearSession(): void {
    localStorage.removeItem('accessToken');
}

export function setSession(token: string): void {
    localStorage.setItem('accessToken', token);
}

export function AuthRequired({ children }: { children: React.ReactElement }) {
    if (isLogged()) {
        return children;
    }
    return <Navigate to="/users/login" replace={true} />;
}