import React, { useEffect, useState } from 'react';
import { Link, Navigate, Outlet } from 'react-router-dom';
import { clearSession, isLogged } from '../authentication/Session';
import '../style/components.css';
import { getIdByToken, getUserStatus, logout } from "../services/usersServices";
import { apiURISize } from "./utils/utils";
import { View, Image, StyleSheet } from 'react-native';
//import save from "./save.png";
//import edit from './utils/edit.png';
///import export from './utils/export.png';
//import module from "./utils/default.svg";
import ImageSVG from './image';

export default function Home() {
    const [loggedIn, setLoggedIn] = useState<boolean>(isLogged());
    const [error, setError] = useState('');
    const [userId, setUserId] = useState('');
    //const [userIsPlaying, setUserIsPlaying] = useState(false);
    //const [userIsInLobby, setUserIsInLobby] = useState(false);
    const [url, setUrl] = useState('');
    const [dotCode, setDotCode] = useState('');
    useEffect(() => {
        const fetchUserData = async () => {
            if (loggedIn) {
                try {
                    const idData = await getIdByToken();
                    setUserId(idData);

                    const userData = await getUserStatus(idData);

                    setError('');
                    /*if (userData.properties.isInLobby) {
                        setUrl(userData.entities[0].links[0].href.substring(apiURISize));
                        setUserIsInLobby(true);
                    } else if (userData.properties.isPlaying) {
                        setUrl(userData.entities[0].links[0].href.substring(apiURISize));
                        setUserIsPlaying(true);
                    } else {
                        setUserIsInLobby(false);
                        setUserIsPlaying(false);
                    }*/
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

    /*if (userIsPlaying) return <Navigate to={`/gomoku` + url} replace={true} />;
    if (userIsInLobby) return <Navigate to={`/gomoku` + url} replace={true} />;*/

    return (
        <div className="containerStyle">
            <div className="headerContainer">
                <Link to="" className="headerStyle">Welcome to GDEAT</Link>
            </div>
            {loggedIn ? (
                <div>
                    <div className="navigationContainerStyle">
                        <>
                            <Link to="/graphs" className="linkStyle">
                                List of Graphs
                            </Link>
                            <Link to={`/gomoku/user/${userId}`} className="linkStyle">
                                Profile
                            </Link>
                            <Link to="/about" className="linkStyle">
                                About
                            </Link>
                            <form onSubmit={handleLogout}>
                                <button className="button-24" role="button">Logout</button>
                            </form>

                        </>
                    </div>
                    <div className="playContainerStyle">
                        <Link to="/graphs/create" className="playStyle">
                            Generate Graph
                        </Link>
                    </div>
                </div>
            ) : (
                <div>
                    <div className="navigationContainerStyle">
                        <>
                            <form method="post" encType="multipart/form-data">
                                <div>
                                    <label >Choose file to upload</label>
                                    <input type="file" id="file" name="file" accept='.txt' />
                                </div>
                                <div>
                                    <button>Submit</button>
                                </div>
                            </form>
                            <div>
                                <textarea value={dotCode} onChange={e => setDotCode(e.target.value)} cols={60} rows={40} />
                                <Link to="/graphs/save" className="linkStyle">
                                    save
                                </Link>
                            </div>
                            <Link to="/users/login" className="linkStyle">
                                Login
                            </Link>
                            <Link to="/users/register" className="linkStyle">
                                Register
                            </Link>
                            <Link to="/graphs" className="linkStyle">
                                Gallery
                            </Link>
                            <Link to="/about" className="linkStyle">
                                about
                            </Link>
                            <div>
                                <ImageSVG />
                                <Link to="/graphs/export" className="linkStyle">
                                    Export
                                </Link>
                                <Link to="/graphs/edit" className="linkStyle">
                                    edit
                                </Link>
                            </div>
                        </>
                    </div>
                    <div className="navigationContainerStyle">
                        <h1 className="loginOrRegister">↑ Login or Register to Create Graph! ↑</h1>
                    </div>
                    <div className="playContainerStyle">
                        <Link to="/graphs/create" className="playStyle">
                            Generate Graph
                        </Link>
                    </div>
                </div>
            )}
            <Outlet />
            {error && <p>{error}</p>}
        </div>
    );
}

