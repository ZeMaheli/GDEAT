// SaveGame.js
import React, { useState, useContext, useEffect } from 'react';
import { AuthContext } from './AuthContext';
import { Link } from 'react-router-dom';

function Warning({ message }) {
  return <div style={{ color: 'red' }}>{message}</div>;
}

function Winner({ message }) {
  return <div style={{ color: 'green' }}>{message}</div>;
}

export function SaveGame() {
  const authData = useContext(AuthContext);
  const [gameInfo, setGameInfo] = useState({
    board: {
      moves: [],
      turn: '',
      winner: 0,
      lastMove: null,
      size: 15,
    },
    id: null,
    openingRule: '',
    player1: null,
    turn: null,
    variante: '',
    winner: 0,
  });

  const [warning, setWarning] = useState('');
  const [winner, setWinner] = useState(0);

  const token = authData.token; /// TOKEN
  const gameId = authData.gameID; // GAMEID
  const [playIndex, setPlayIndex] = useState(0);
  const [nOfMoves, setNOfMoves] = useState(0);

  const fetchData = async () => {
    try {
      const response = await fetch(`http://localhost:8081/api/games/replay/${gameId}`, {
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/vnd.siren+json',
          Authorization: 'Bearer '+ token,
        },
      });
      const data = await response.json();
      console.log('data = ' + JSON.stringify(data));
      const props = data.properties;
      console.log('Received data:', props);

      setNOfMoves(data.properties.length);
      console.log('properties = ' + data.properties);
      console.log('length = ' + data.properties.length);
      console.log('movesSize = ' + nOfMoves);
      console.log('array = ' + data.properties[playIndex]);
      console.log('moves = ' + data.properties[playIndex].board.moves);

      setGameInfo((prevGameInfo) => ({
        ...prevGameInfo,
        board: {
          moves: data.properties[playIndex].board.moves,
          turn: data.properties[playIndex].board.turn,
          winner: data.properties[playIndex].board.winner,
          lastMove: data.properties[playIndex].board.lastMove,
          size: data.properties[playIndex].variante === 'OMOK' ? 19 : 15,
        },
        id: data.properties[playIndex].game_id,
        openingRule: data.properties[playIndex].openingRule,
        player1: data.properties[playIndex].player,
        turn: data.properties[playIndex].turn,
        variante: data.properties[playIndex].variant,
        winner: data.properties[playIndex].board.winner,
      }));
      console.log('GAMEINFO: ' + JSON.stringify(gameInfo));
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const renderRows = () => {
    const moves = gameInfo.board?.moves || {};

    return Array.from({ length: gameInfo.board.size }, (_, i) => (
      <tr key={i}>
        <td style={{ textAlign: 'right', width: '30px' }}>{i + 1}</td>
        {Array.from({ length: gameInfo.board.size }, (_, j) => {
          const cellKey = `${i + 1}${String.fromCharCode(65 + j)}`;
          const cell = moves[cellKey];

          return (
            <td
              key={j}
              style={{
                backgroundColor: getCellColor(cell),
                width: '30px',
                height: '30px',
                textAlign: 'center',
                cursor: 'pointer',
                border: '1px solid black',
                borderTop: i === 0 ? '1px solid black' : 'none',
                borderLeft: j === 0 ? '1px solid black' : 'none',
              }}
            >
              {getCellContent(cell)}
            </td>
          );
        })}
      </tr>
    ));
  };

  const handleGameIdChange = (event) => {
    const newGameId = event.target.value;
    authData.setGameID(newGameId); // Assuming setGameID is a function to update the game ID in AuthContext
  };

  const renderColumnLetters = () => {
    const letters = Array.from({ length: gameInfo.board.size }, (_, index) =>
      String.fromCharCode(65 + index)
    );

    return (
      <tr>
        <td style={{ textAlign: 'center', width: '30px' }}></td>
        {letters.map((letter, index) => (
          <td key={index} style={{ textAlign: 'center', width: '30px' }}>
            {letter}
          </td>
        ))}
      </tr>
    );
  };

  const getCellColor = (cell) => {
    if (cell === null || cell === 'EMPTY') return 'white';
    return cell === 'PLAYER_X' ? 'red' : 'blue';
  };

  const getCellContent = (cell) => {
    if (cell === null) return null;
    return cell === 'PLAYER_X' ? 'X' : cell === 'PLAYER_O' ? 'O\n' : null;
  };

  const handleClickPrevious = () => {
    if (playIndex > 0) {
      setPlayIndex((x) => x - 1);
      
    } else {
      setWarning('no previous play');
    }
  };

  const handleClickNext = () => {
    if (playIndex < nOfMoves - 1) {
      setPlayIndex((x) => x + 1);
    } else {
      setWarning('no Next play');
    }
  };

  return (
    <div>
        <h1>ReplayGame</h1>
        <h2>Turn: {gameInfo.turn}</h2>
      <div>
       
        <label htmlFor="gameIdInput">Enter Game ID: </label>
        <input
          type="text"
          id="gameIdInput"
          value={gameId || ''}
          onChange={handleGameIdChange}
        />
        <button onClick={fetchData}>Fetch Game</button>
        <button onClick={handleClickPrevious}>previous</button>
        <button onClick={handleClickNext}>next</button>
      </div>
      <Link to="/me">UserHome</Link>
      <table
        style={{
          borderCollapse: 'collapse',
          margin: '10px',
        }}
      >
        <tbody>
          {renderColumnLetters()}
          {renderRows()}
        </tbody>
      </table>
    </div>
  );
}

export default SaveGame;
