import * as React from 'react';
import { createContext, useContext, useState, useEffect, useCallback } from 'react';

/**
 * Holds the session data.
 *
 * @property username the username of the user
 * @property userHomeLink the user home link
 */
export interface Session {
    readonly username: string;
    readonly userHomeLink: string;
}

/**
 * The manager for the session.
 *
 * @property session the session data
 * @property setSession sets the session data
 * @property clearSession clears the session data
 */
export interface SessionManager {
    readonly session: Session | null;
    readonly setSession: (session: Session) => void;
    readonly clearSession: () => void;
}

const SessionManagerContext = createContext<SessionManager>({
    session: null,
    setSession: () => {},
    clearSession: () => {},
});

const sessionStorageKey = 'session';

/**
 * Provides the session data to the children.
 *
 * @param children the children to render
 */
export function Auth({ children }: { children: React.ReactNode }) {
    const [session, setSession] = useState<Session | null>(() => {
        const sessionJson = localStorage.getItem(sessionStorageKey);
        if (!sessionJson) return null;

        try {
            return JSON.parse(sessionJson) as Session;
        } catch (e) {
            console.error('Failed to parse session from localStorage:', e);
            return null;
        }
    });

    const saveSession = useCallback((session: Session) => {
        setSession(session);
        localStorage.setItem(sessionStorageKey, JSON.stringify(session));
    }, []);

    const clearSession = useCallback(() => {
        localStorage.removeItem(sessionStorageKey);
        setSession(null);
    }, []);

    return (<SessionManagerContext.Provider
        value={{
            session: session,
            setSession: saveSession,
            clearSession: clearSession
        }}>
        {children}
    </SessionManagerContext.Provider>);
}

/**
 * Returns the session data.
 *
 * @return the session data
 */
export function useSession(): Session | null {
    const { session } = useContext(SessionManagerContext);
    return session;
}

/**
 * Returns the session manager.
 *
 * @return the session manager
 */
export function useSessionManager(): SessionManager {
    return useContext(SessionManagerContext);
}

/**
 * Checks whether the user is logged in or not.
 *
 * @return true if the user is logged in, false otherwise
 */
export function useLoggedIn(): boolean {
    const { session } = useSessionManager();
    return session !== null;
}
