/*import React, {useState} from 'react';
import {Link, useNavigate } from "react-router-dom";
import {getLeaderboard} from "../services/usersServices";
import '../style/components.css';
import {apiURISize} from "./utils/utils";

export default function Leaderboard() {
    const navigate = useNavigate();
    const [leaderboardData, setLeaderboardData] = useState<any>('');
    const [start, setStart] = useState<number>(0);
    const [max, setMax] = useState<number>(2);

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
        getLeaderboard(start, max)
            .then((data) => {
                setLeaderboardData(data)
                navigate(`/gomoku/leaderboard?start=${start}&max=${max}`);
            })
            .catch((error) => {
                console.error(error);
            })
    }

    return (
        <div className="leaderBoardContainerStyle">
            <h1 className="leaderBoardHeaderStyle">Leaderboard</h1>
            {leaderboardData !== '' ? (
                <div className={"leaderBoardListContainerStyle"}>
                    <ul className={"leaderBoardListStyle"}>
                        {leaderboardData.entities.map((user: any) => (
                            <li key={user.properties.username} className={"leaderBoardListItemStyle"}>
                                <strong>Rank:</strong> {user.properties.rankingPosition}<br/>
                                <strong>Username:</strong>
                                <Link
                                    to={`/gomoku`+user.links[0].href.substring(apiURISize)}>
                                    {user.properties.username}</Link><br/>
                                <strong>Games:</strong> {user.properties.games}<br/>
                                <strong>Score:</strong> {user.properties.score}
                            </li>
                        ))}
                    </ul>
                </div>
            ) : (
                <p className={"leaderBoardLoadingStyle"}>Loading...</p>
            )}
            <form onSubmit={handleSubmit} className={"leaderBoardFormStyle"}>
                <label className={"leaderBoardLabelStyle"}>
                    Start:
                    <input type="number" value={start} onChange={handleStartChange}
                           className={"leaderBoardInputStyle"}/>
                </label>
                <label className={"leaderBoardLabelStyle"}>
                    Max:
                    <input type="number" value={max} onChange={handleMaxChange} className={"leaderBoardInputStyle"}/>
                </label>
                <button type="submit" className={"leaderBoardSubmitButtonStyle"}>
                    Search
                </button>
            </form>
        </div>
    );
};
*/