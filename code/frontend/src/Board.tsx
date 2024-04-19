import React, { createContext, useEffect, useState, useContext } from 'react';
import { Logout } from './logout';
import { AuthContext } from './AuthContext';
import {
  createBrowserRouter,
  RouterProvider,
  Link,
  useNavigate,
} from 'react-router-dom';

function Warning({ message }) {
  return <div style={{ color: 'red' }}>{message}</div>;
}

function Winner({ message }) {
  return <div style={{ color: 'green' }}>{message}</div>;
}

export function Board() {
  let authData = useContext(AuthContext);
  const [gameInfo, setGameInfo] = useState({
    board: { moves: {}, turn: '', winner: 0, lastMove: null, size: 15 },
    id: null,
    openingRule: '',
    player1: null,
    player2: null,
    turn: null,
    variante: '',
    winner: 0,
  });

  const [warning, setWarning] = useState('');
  const [winner, setWinner] = useState(0);

  const token = authData.token; /// TOKEN
  const gameId = authData.gameID; // GAMEID

  const fetchData = async () => {
    try {
      const response = await fetch(`http://localhost:8081/api/games/get/${gameId}`, {
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/vnd.siren+json',
          authorization: 'Bearer ' + token,
        },
      });
      const data = await response.json();
      if (data.properties.board.winner !== null && data.properties.board.winner !== 0) {
        console.log('ENTREI NO WINNER-first if: ' + data.properties.winner);
        setWinner(data.properties.board.winner);
      }

      console.log('Received data:', data);
      if(data.properties.winner !== null && data.properties.winner !== 0) {
        console.log('ENTREI NO WINNER-second if: ' + data.properties.winner);
        setWinner(data.properties.winner);
      }

      setGameInfo({
        board: {
          moves: data.properties.board.moves,
          turn: data.properties.board.turn,
          winner: data.properties.board.winner,
          lastMove: data.properties.board.lastMove,
          size: data.properties.variante === 'OMOK' ? 19 : 15,
        },
        id: data.properties.id,
        openingRule: data.properties.openingRule,
        player1: data.properties.player1,
        player2: data.properties.player2,
        turn: data.properties.turn,
        variante: data.properties.variante,
        winner: data.properties.winner,
      });

      console.log('GAMEINFO: ' + gameInfo);

      // Check if the response indicates that the game is over
      if (data.properties.board.winner !== null && data.properties.board.winner !== 0) {
        console.log('ENTREI NO WINNER: ' + data.properties.winner);
        setWinner(data.properties.winner);
      } else {
        setWarning('');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleClick = async (i, j) => {
    if(gameInfo.winner !== 0) {
      setWarning('The game is over!, player ' + gameInfo.winner + ' won!');
    }
    try {
      if (gameInfo.turn === authData.id) {
        console.log('WINNER: ' + gameInfo.winner);
        if (gameInfo.winner != null) {
          setWinner(gameInfo.winner);
        }

        const requestBody = {
          userId: authData.id,
          l: i + 1,
          c: String.fromCharCode(65 + j),
        };

        const requestOptions = {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Accept: 'application/vnd.siren+json',
            authorization: 'Bearer ' + token,
          },
          body: JSON.stringify(requestBody),
        };

        const response = await fetch(`http://localhost:8081/api/games/play/${gameId}`, requestOptions);
        const data = await response.json();

        await fetchData();

        console.log(data);

        if (gameInfo.turn === 'PLAYER_O') {
          console.log('WINNER: ' + gameInfo.winner);
          if (gameInfo.winner != null) {
            console.log('ENTREI NO WINNER: ' + gameInfo.winner);
            setWinner(gameInfo.winner);
          }
          setGameInfo((prevGameInfo) => ({
            ...prevGameInfo,
            board: {
              ...prevGameInfo.board,
              moves: {
                ...prevGameInfo.board.moves,
                [`${i + 1}${String.fromCharCode(65 + j)}`]: 'PLAYER_O',
              },
            },
          }));

          setGameInfo((prevGameInfo) => ({
            ...prevGameInfo,
            turn: 'PLAYER_X',
          }));
        }
      } else {
        if (gameInfo.winner != null) {
          console.log('ENTREI NO WINNER: ' + gameInfo.winner);
          setWinner(gameInfo.winner);
        }
        setWarning("It's not your turn!");
        setTimeout(() => {
          setWarning('');
        }, 3000);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  useEffect(() => {
    fetchData();
    const fetchInterval = 2000;
    const fetchDataWithDelay = async () => {
      await fetchData();
      setTimeout(fetchDataWithDelay, fetchInterval);
    };
    const initialFetchTimeout = setTimeout(fetchDataWithDelay, fetchInterval);
    return () => clearTimeout(initialFetchTimeout);
  }, [gameInfo.winner, gameInfo.turn, authData.id, authData.token, gameId]);

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
              onClick={() => handleClick(i, j)}
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

  return (
    <div>
      <h2>Turn: {gameInfo.turn}</h2>
      <Link to="/me">UserHome</Link>
      {warning && <Warning message={warning} />}
      {winner && <Winner message={'o jogador que Ganhou este jogo foi o jogador ' + winner} />}
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