import React, { useState } from 'react';
import { Link, Navigate } from 'react-router-dom';
import '../style/components.css';

export default function GameConfiguration() {
    const [boardSize, setBoardSize] = useState<number>(7);
    const [gameVariant, setGameVariant] = useState<string>('normal');
    const [redirect, setRedirect] = useState(false);

    if (redirect) return <Navigate to={`/gomoku/matchmaking?boardSize=${boardSize}&variant=${gameVariant}`} replace={true} />;

    const handleStartChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = Number(event.target.value);
        if (!isNaN(value) && value >= 5 && value <= 25) {
            setBoardSize(value);
        }
    };

    const handleVariationChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setGameVariant(event.target.value);
    };

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setRedirect(true);
    };

    return (
        <div className="leaderBoardContainerStyle">
            <Link className={"home-redirect"} to="/" style={homeLinkStyle}>Home</Link>
            <h1 className="leaderBoardHeaderStyle">Game Configuration</h1>
            <form onSubmit={handleSubmit} className={'leaderBoardFormStyle'}>
                <label className={'leaderBoardLabelStyle'}>
                    Game variation:
                    <select value={gameVariant} onChange={handleVariationChange} id={"game-variation"} className={'leaderBoardInputStyle'}>
                        <option value="normal">Normal</option>
                        <option value="normal-inv">Normal - Inverted</option>
                        <option value="swap-1">Swap 1</option>
                        <option value="swap-2">Swap 2</option>
                        <option value="swap-3">Swap 3</option>
                        <option value="swap-4">Swap 4</option>
                        <option value="double-turn">Double Turn</option>
                        <option value="double-turn-inv">Double Turn - Inverted</option>
                    </select>
                </label>
                <label className={'leaderBoardLabelStyle'}>
                    Board size:
                    <input type="number" value={boardSize} onChange={handleStartChange} id={"game-size"} className={'leaderBoardInputStyle'} />
                </label>
                <button type="submit" id={"play-game"} className={'leaderBoardSubmitButtonStyle'}>
                    Play
                </button>
            </form>
        </div>
    );
}

const homeLinkStyle: React.CSSProperties = {
    fontSize: '20px',
    display: 'block',
    textAlign: 'center',
    marginTop: '20px',
    marginBottom: '5px',
};

