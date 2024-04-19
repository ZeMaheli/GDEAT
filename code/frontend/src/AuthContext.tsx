// AuthContext.js
import React, { createContext, useContext, useState } from 'react';

 // No need to pass any default value

 type ContextType = {
    loggedin: boolean,
    setLoggedin: (f: (boolean) => boolean) => void,
    id: number,
    setId: (f: (number) => number) => void
    token: string,
    setToken: (f: (string) => string) => void,
    gameID: number,
    setGameID: (f: (number) => number) => void,
}
 export const AuthContext = createContext<ContextType>({
    loggedin: false, id: 0, token: "",gameID: 0,
    setLoggedin: function (f: (boolean: any) => boolean): void {
        throw new Error('Function not implemented.');
    },
    setId: function (f: (number: any) => number): void {
        throw new Error('Function not implemented.');
    },
    setToken: function (f: (string: any) => string): void {
        throw new Error('Function not implemented.');
    },
    setGameID: function (f: (number: any) => number): void {
        throw new Error('Function not implemented.');
    },
});





