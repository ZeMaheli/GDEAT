import React, {useEffect, useState} from 'react';
import {Link, Navigate, Outlet} from 'react-router-dom';
import {clearSession, isLogged} from '../authentication/Session';
import '../style/components.css';
import {getIdByToken, getUserStatus, logout} from "../services/usersServices";
import {apiURISize} from "./utils/utils";

export default function Home() {
    const [loggedIn, setLoggedIn] = useState<boolean>(isLogged());
    const [error, setError] = useState('');
    const [userId, setUserId] = useState('');
    const [userIsPlaying, setUserIsPlaying] = useState(false);
    const [userIsInLobby, setUserIsInLobby] = useState(false);
    const [url, setUrl] = useState('');

    useEffect(() => {
        const fetchUserData = async () => {
            if (loggedIn) {
                try {
                    const idData = await getIdByToken();
                    setUserId(idData);

                    const userData = await getUserStatus(idData);

                    setError('');
                    if (userData.properties.isInLobby) {
                        setUrl(userData.entities[0].links[0].href.substring(apiURISize));
                        setUserIsInLobby(true);
                    } else if (userData.properties.isPlaying) {
                        setUrl(userData.entities[0].links[0].href.substring(apiURISize));
                        setUserIsPlaying(true);
                    } else {
                        setUserIsInLobby(false);
                        setUserIsPlaying(false);
                    }
                } catch (error) {
                    setError(error.message);
                    clearSession();
                    setLoggedIn(false);
                }
            }
        };

        fetchUserData();
    }, [loggedIn]);

    const handleLogout = async (ev: React.FormEvent<HTMLFormElement>): Promise<boolean> => {
        ev.preventDefault();
        try {
            const data = await logout();
            console.log('Logout successful', data);
            setLoggedIn(false);
            clearSession();
            return true;
        } catch (error) {
            console.error('Logout failed', error);
            setError(error.message);
            throw error;
        }
    };

    if (userIsPlaying) return <Navigate to={`/gomoku` + url} replace={true}/>;
    if (userIsInLobby) return <Navigate to={`/gomoku` + url} replace={true}/>;

    return (
        <div className="containerStyle">
            <div className="headerContainer">
                <Link to="" className="headerStyle">Welcome to Gomoku</Link>
            </div>
            {loggedIn ? (
                <div>
                    <div className="navigationContainerStyle">
                        <>
                            <Link to="/gomoku/leaderboard" className="linkStyle">
                                Leaderboard
                            </Link>
                            <Link to={`/gomoku/user/${userId}`} className="linkStyle">
                                Profile
                            </Link>
                            <Link to="/gomoku/about" className="linkStyle">
                                About
                            </Link>
                            <form onSubmit={handleLogout}>
                                <button className="button-24" role="button">Logout</button>
                            </form>
                        </>
                    </div>
                    <div className="playContainerStyle">
                        <Link id={"play-config"} to="/gomoku/configuration" className="playStyle">
                            Play
                        </Link>
                    </div>
                    <div className="playContainerStyle">
                        <Link to="/gomoku/games" className="playStyle">
                            Games
                        </Link>
                    </div>
                </div>
            ) : (
                <div>
                    <div className="navigationContainerStyle">
                        <>
                            <Link to="/gomoku/login" className="linkStyle">
                                Login
                            </Link>
                            <Link to="/gomoku/register" className="linkStyle">
                                Register
                            </Link>
                            <Link to="/gomoku/leaderboard" className="linkStyle">
                                Leaderboard
                            </Link>
                            <Link to="/gomoku/about" className="linkStyle">
                                About
                            </Link>
                        </>
                    </div>
                    <div className="navigationContainerStyle">
                        <h1 className="loginOrRegister">↑ Login or Register to play! ↑</h1>
                    </div>
                    <div className="playContainerStyle">
                        <Link to="/gomoku/games" className="playStyle">
                            Games
                        </Link>
                    </div>
                </div>
            )}
            <Outlet/>
            {error && <p>{error}</p>}
        </div>
    );
}

