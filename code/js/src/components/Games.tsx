/*import React, {useEffect, useState} from 'react';
import {getUserById} from "../services/usersServices";
import {getGames} from "../services/gamesServices";
import {Link, useNavigate} from "react-router-dom";
import "../style/components.css"
import {apiURISize} from "./utils/utils";

export default function Games() {
    const navigate = useNavigate();
    const [gamesData, setGamesData] = useState<any>('');
    const [start, setStart] = useState<number>(0);
    const [max, setMax] = useState<number>(2);
    const [playerWId, setPlayerWId] = useState<string>('');
    const [playersInfo, setPlayersInfo] = useState<{ [key: string]: { playerW: string; playerB: string } }>({});

    const handleStartChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = Number(event.target.value);
        if (!isNaN(value) && value >= 0 && value <= max) {
            setStart(value);
        }
    };

    const handleMaxChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = Number(event.target.value);
        if (!isNaN(value) && value >= 0 && value >= start && value <= 100) {
            setMax(value);
        }
    };

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        getGames(start, max).then((data) => {
            setGamesData(data);
            navigate(`/gomoku/games?start=${start}&max=${max}`);
        })
        .catch((error) => {
            console.error(error);
        })
    };

    const fetchUserData = async (userId: string) => {
        try {
            const userData = await getUserById(userId);
            return userData.properties;
        } catch (error) {
            console.error(error);
        }
    }

    useEffect(() => {
        const updatePlayersInfo = async () => {
            const playersInfoMap: { [key: string]: { playerW: string; playerB: string } } = {};
            console.log(gamesData)
            if (gamesData !== '') {
                await Promise.all(gamesData.entities.map(async (game: any) => {
                    const playerW = await fetchUserData(game.properties.playerW);
                    const playerB = await fetchUserData(game.properties.playerB);
                    setPlayerWId(playerW.userID)
                    const wUsername = playerW.username;
                    const bUsername = playerB.username;

                    playersInfoMap[game.properties.id] = {
                        playerW: wUsername,
                        playerB: bUsername,
                    };
                }));

                setPlayersInfo(playersInfoMap);
            }
        };

        updatePlayersInfo();
    }, [gamesData]);

    return (
        <div className="containerStyle">
            <h1 className="gamesHeaderStyle">Games</h1>
            <div>
                <Link to="/" className="gamesHomeLink"> Home</Link>
            </div>
            {gamesData !== '' ? (
                <div className="gamesListContainerStyle">
                    <ul className="gamesListStyle">
                        {gamesData.entities.map((game: any) => (
                            <Link
                                key={game.properties.id}
                                to={`/gomoku`+game.links[0].href.substring(apiURISize)}
                            >
                            <li className="gamesListItemStyle">
                                <p className="gamesListItemText">
                                    <strong>Player W:</strong> {playersInfo[game.properties.id]?.playerW}
                                </p>
                                <p className="gamesListItemText">
                                    <strong>Player B:</strong> {playersInfo[game.properties.id]?.playerB}
                                </p>
                                {game.properties.winner !== null && (
                                    <>
                                        <p className="gamesListItemText"><strong>Winner:</strong> {game.properties.winner === playerWId ?
                                            playersInfo[game.properties.id]?.playerW
                                            : playersInfo[game.properties.id]?.playerB
                                        }</p>
                                        <p className="gamesListItemText"><strong>Winning Position:</strong> {game.properties.winningPos}</p>
                                    </>
                                )}
                                {game.properties.isDraw && <p className="gamesListItemText"><strong>The game ended in a draw.</strong></p>}
                            </li>
                            </Link>
                        ))}
                    </ul>
                </div>
            ) : (
                <p className="gamesLoadingStyle">Loading...</p>
            )}
            <form onSubmit={handleSubmit} className="gamesFormStyle">
                <label className="gamesLabelStyle">
                    Start:
                    <input type="number" value={start} onChange={handleStartChange} className="gamesInputStyle" />
                </label>
                <label className="gamesLabelStyle">
                    Max:
                    <input type="number" value={max} onChange={handleMaxChange} className="gamesInputStyle" />
                </label>
                <button type="submit" className="gamesSubmitButtonStyle">
                    Search
                </button>
            </form>
        </div>
    );
};

*/