import React, {useEffect, useRef, useState} from 'react';
import {Box} from "./Box";
import {Link, Navigate, useNavigate, useParams} from "react-router-dom";
import {getSessionToken} from "../authentication/Session";
import Popup from 'reactjs-popup';
import {getIdByToken, getUserById} from "../services/usersServices";
import {leaveGame, leaveLobby, play, pollGame} from "../services/gamesServices";

export default function Game(){
    const [state, setState] = useState<string>("Playing");
    const [game, setGame] = useState<any>('');
    const [winner, setWinner] = useState<string>('');
    const [redirectHome, setRedirectHome] = useState(false)
    const [playerW, setPlayerW] = useState<any>('');
    const [playerB, setPlayerB] = useState<any>('');
    const [board, setBoard] = useState<any>([]);
    const [turn, setTurn] = useState<string>('');
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
        setState("Loading")
        intervalRef.current = setInterval(async () => {
            const gameData = await pollGame(gameId)
            setGame(gameData)
            if (!playerW || !playerB) {
                // Fetch player names only if they haven't been fetched yet
                const playerWData = await getUserById(gameData.entities[0].properties.playerW)
                setPlayerW(playerWData.properties.username);
                const playerBData = await getUserById(gameData.entities[1].properties.playerB)
                setPlayerB(playerBData.properties.username);
                if(gameData.entities[0].properties.turn == playerWData) {
                    setTurn(playerWData.properties.username);
                } else {
                    setTurn(playerBData.properties.username);
                }
            }
            if(gameData.properties.positions != "") {
                gameData.properties.positions.split("|").forEach(pos => {
                    const posList = pos.split(',');
                    const line = parseInt(posList[0]);
                    const column = parseInt(posList[1]);
                    b[line][column] = posList[2];
                })
            }
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
        gameplay()
    },[])

    const leaveGameplay = async () => {
        clearInterval(intervalRef.current);
        setRedirectHome(true);
    };

    if(redirectHome)  return <Navigate to="/" replace={true}/>

    const renderBoard = () => {
        if (!game) {
            return <p>Loading...</p>;
        }

        return board.map((line, rowIndex) => {
            return (
                <div key={rowIndex}>
                    {line.map((box, columnIndex) => {
                        return (
                            <Box
                                key={columnIndex}
                                value={box}
                                onClick={undefined}
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
            <button className="button-24" onClick={leaveGameplay}>Leave Game</button>
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
                            {winner!='' ? (
                                <div className="playerInfo">
                                    <p><strong>Winner:</strong> {winner}</p>
                                </div>
                            ) : (
                                <div className="playerInfo">
                                    <p><strong>Turn: </strong> {turn}</p>
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
}