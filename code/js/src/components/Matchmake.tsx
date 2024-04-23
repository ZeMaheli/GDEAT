import React, {useEffect, useState, useRef} from 'react';
import {Navigate, useLocation} from "react-router-dom";
import "../style/components.css";
import {getIdByToken, getUserById, getUserStatus} from "../services/usersServices";
import {leaveLobby, matchmake, pollLobby} from "../services/gamesServices";

export default function Matchmake() {
    const [state, setState] = useState<string>('');
    const [lobbyId, setLobbyId] = useState<string>('');
    const [userId, setUserId] = useState<string>('');
    const [playerW, setPlayerW] = useState<string>('');
    const [playerB, setPlayerB] = useState<string>('');
    const [url, setUrl] = useState<string>('');
    const [redirectGame, setRedirectGame] = useState(false)
    const [redirectHome, setRedirectHome] = useState(false)
    const intervalRef = useRef<NodeJS.Timeout | undefined>();
    const query = new URLSearchParams(useLocation().search);
    const boardSize = query.get('boardSize')
    const variant = query.get('variant')


    const fetchLobbyData = async () => {
        try {
            const userData = await getIdByToken();
            setUserId(userData);
            const userStatus = await getUserStatus(userData)
            if (userStatus.properties.isPlaying) {
                clearInterval(intervalRef.current)
                setUrl(userStatus.properties.id)
                setRedirectGame(true);
                return;
            }
            let lId = userStatus.id;
            if(!userStatus.properties.isInLobby){
                const lobbyData = await matchmake(userData, boardSize, variant);
                lId = lobbyData.properties.lobbyID;
            }
            setState('Searching')
            let lobbyPollData = await pollLobby(lId, userData)
            intervalRef.current = setInterval(async () => {
                if (redirectGame) clearInterval(intervalRef.current);
                if (lobbyPollData.properties.isLobbyFull) {
                    clearInterval(intervalRef.current);

                    const playerWData = await getUserById(lobbyPollData.entities[0].properties.playerW)
                    setPlayerW(playerWData.properties.username);

                    const playerBData = await getUserById(lobbyPollData.entities[1].properties.playerB)
                    setPlayerB(playerBData.properties.username);
                    setUrl(lobbyPollData.entities[2].properties.gameID);
                    setState('MatchFound');
                    setTimeout(() => {
                        setRedirectGame(true)
                    }, 3000)
                } else{
                    lobbyPollData = await pollLobby(lId, userData)
                    setLobbyId(lobbyPollData.properties.lobbyID);
                }
            }, 2000);
            return () => clearInterval(intervalRef.current);
        } catch (error) {
            console.error(error);
        }
    }

    useEffect(() => {
        fetchLobbyData();
    }, []);

    const leaveMatchmake = async () => {
        try {
            await leaveLobby(lobbyId, userId).then(() => {
                clearInterval(intervalRef.current);
                setRedirectHome(true);
            });
        } catch (error) {
            console.error(error);
        }
    };

    if(redirectGame)  return <Navigate to={`/gomoku/game/${url}/play`} replace={true}/>

    if(redirectHome)  return <Navigate to="/" replace={true}/>

    return (
        <div>
            {state === 'MatchFound' ? (
                <div className="containerStyle">
                    <div className="headerContainer">
                        <h1 className="headerStyle">Match Found!</h1>
                        <h2>{playerW} vs {playerB}</h2>
                    </div>
                </div>
            ) : (
                <>
                    {lobbyId === '' ? (
                        <>
                            <div className="containerStyle">
                                <div className="headerContainer">
                                    <h1>Looking for Lobby...</h1>
                                </div>
                            </div>
                        </>
                    ) : (
                        <>
                            <button className="button-24" onClick={leaveMatchmake}>Leave Lobby</button>
                            <div className="containerStyle">
                                <div className="headerContainer">
                                    <h1>Searching for opponent...</h1>
                                </div>
                        </div>
                        </>
                    )
                    }
                </>
            )}
        </div>
    )
}