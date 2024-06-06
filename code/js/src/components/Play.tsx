/*import React, {useEffect, useRef, useState} from 'react';
import {Box} from "./Box";
import {Link, Navigate, useNavigate, useParams} from "react-router-dom";
import {getSessionToken} from "../authentication/Session";
import Popup from 'reactjs-popup';
import {getIdByToken, getUserById} from "../services/usersServices";
import {leaveGame, play, pollGame} from "../services/gamesServices";
import "../style/components.css";

export default function Play(){
    const [state, setState] = useState<string>("PlayerTurn");
    const [userId, setUserId] = useState<string>('');
    const [game, setGame] = useState<any>('');
    const [winner, setWinner] = useState<string>('');
    const [redirectHome, setRedirectHome] = useState(false)
    const [playerW, setPlayerW] = useState<any>('');
    const [playerB, setPlayerB] = useState<any>('');
    const [board, setBoard] = useState<any>([]);
    const [timeLeft, setTimeLeft] = useState<string>('');
    const intervalRef = useRef<NodeJS.Timeout | undefined>();
    const gameId = useParams().gameId

    const initializeBoard = (size) => {
        let newBoard = [];
        for (let i = 0; i < size; i++) {
            let line = [];
            for (let j = 0; j < size; j++) {
                line.push('‎ ');
            }
            newBoard.push(line);
        }
        setBoard(newBoard);
        return newBoard;
    };

    const gameplay = async () => {
        const bsize = await pollGame(gameId).then((gameData) => {
            return gameData.properties.boardSize;
        });
        const b = initializeBoard(bsize);

        const userData = await getIdByToken()
        setUserId(userData)
        setState("Loading")
        intervalRef.current = setInterval(async () => {
            const gameData = await pollGame(gameId)
            setGame(gameData)
            const timeLeft = 300 - gameData.properties.timeSinceLastPlay
            setTimeLeft(formatTime(timeLeft))
            if (!playerW || !playerB) {
                // Fetch player names only if they haven't been fetched yet
                const playerWData = await getUserById(gameData.entities[0].properties.playerW)
                setPlayerW(playerWData.properties.username);
                const playerBData = await getUserById(gameData.entities[1].properties.playerB)
                setPlayerB(playerBData.properties.username);
            }
            if(gameData.properties.positions != "") {
                gameData.properties.positions.split("|").forEach(pos => {
                    const posList = pos.split(',');
                    const line = parseInt(posList[0]);
                    const column = parseInt(posList[1]);
                    b[line][column] = posList[2];
                })
            }
            if (gameData.entities[2].properties.turn == userData) setState("PlayerTurn");
            else setState("OpponentTurn")
            if (gameData.properties.isWin) {
                const winnerData = await getUserById(gameData.entities[3].properties.winner)
                setWinner(winnerData.properties.username);
                setState("GameOver")
                clearInterval(intervalRef.current);
            }
            if (gameData.properties.isDraw) {
                setState("GameOver")
                clearInterval(intervalRef.current);
            }
        }, 1000);
        return () => clearInterval(intervalRef.current);
    }

    useEffect(() => {
        gameplay();
    },[])

    const formatTime = (time) => {
        const minutes = Math.floor(time / 60);
        const seconds = Math.floor(time % 60);
        return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    };

    const handleBoxClick = async (line, column) => {
        try {
            if (state === 'PlayerTurn') {
                // Only make a play if it's the player's turn
                const playData = await play(gameId, userId, line, column)
                setGame(playData);
                if (playData.properties.positions != "") {
                    playData.properties.positions.split("|").forEach(pos => {
                        const posList = pos.split(',');
                        const line = parseInt(posList[0]);
                        const column = parseInt(posList[1]);
                        board[line][column] = posList[2];
                    })
                }
            }
        }
        catch (error) {console.error(error)}
    }

    const leaveGameplay = async () => {
        try {
            await leaveGame(gameId, userId).then(() => {
                clearInterval(intervalRef.current);
                setRedirectHome(true);
            });
        } catch (error) {
            console.error(error);
        }
    };

    if(redirectHome)  return <Navigate to="/" replace={true}/>

    const renderBoard = () => {
        return board.map((line, rowIndex) => {
            return (
                <div key={rowIndex}>
                    {line.map((box, columnIndex) => {
                        return (
                            <Box
                                key={columnIndex}
                                value={box}
                                onClick={() => handleBoxClick(rowIndex, columnIndex)}
                            />
                        );
                    })}
                </div>
            );
        });
    }

    const renderPopup = () => {
        return (
            <Popup open={state === 'GameOver'} modal className="my-popup">
                <div className="popupDiv">
                    <p className="popupH1">Game Over!</p>
                    {winner ? (
                        <p className="popupH1">Winner: {winner}</p>
                    ) : (
                        <p className="popupH1">It's a Draw!</p>
                    )}
                    <Link to="/" className="popupLinkStyle">Home</Link>
                </div>
            </Popup>
        );
    };


    return (
        <div>
            {winner=='' ? (
                <button className="button-24" onClick={leaveGameplay}>Leave Game</button>
            ) : (
                <Link to="/" className="popupLinkStyle">Home</Link>
            )}
            <div className="gameContainerStyle">
                <h1 className="gameHeader">Game</h1>
                {game ?(
                    <div className="gameInnerContainerStyle">
                        <div className="playerContainer">
                            <div className="playerInfo">
                                <p><strong>Player W:</strong> {playerW}</p>
                            </div>
                            <div className="playerInfo">
                                <p><strong>Player B:</strong> {playerB}</p>
                            </div>
                        </div>
                        {renderBoard()}
                        <div className="playerContainer">
                            {winner=='' ? (
                                <div className="playerContainer">
                                    <div className="playerInfo">
                                        <p><strong>{game.entities[2].properties.turn==userId ?("Your Turn"):("Opponent's Turn")}</strong></p>
                                    </div>
                                    <div className="playerInfo">
                                        <p><strong>Time left:</strong> {game.properties.timeSinceLastPlay > 0 ? (
                                            timeLeft
                                        ) : (
                                            ""
                                        )}</p>
                                    </div>
                                </div>
                            ) : (
                                <div className="playerInfo">
                                    <p><strong>Winner:</strong> {winner}</p>
                                </div>
                            )
                            }
                        </div>
                    </div>
                ) : (<>{renderBoard()}</>)
                }
            </div>
            {renderPopup()}
        </div>
    )
}*/