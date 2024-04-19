import React, { createContext, useEffect, useState, useContext } from 'react';
import { Logout } from './logout';

import { AuthContext } from './AuthContext';
import { createRoot } from 'react-dom/client';
import {
    createBrowserRouter,
    RouterProvider,
    Link,
    useNavigate,
} from 'react-router-dom';

export function UserHome(){
    return (
      <div>
          <h1>"User Home"</h1>
          <p>
             
              <Link to="/">Home</Link>{' '}
              <Link to="/logout">Logout</Link>{' '}
              <Link to="/matchmaking">Matchmaking</Link>{' '}
              <Link to="/ranking">Ranking</Link>{' '}
                <Link to="/saveGame">ReplayGames</Link>{' '}
            
          </p>
      </div>
  );
  }