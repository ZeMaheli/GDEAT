import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {getUserById} from "../services/usersServices";
import "../style/components.css"

export default function UserDetails() {
    const [userData, setUserData] = useState<any>('');
    const userId = useParams().id;

    useEffect(() => {
        getUserById(userId)
            .then((data) => {
                setUserData(data)
            })
    }, [])

    return (
        <div className="user-details">
            {userData ? (
                <div className="userDetailsContainer">
                    <h1>User Details</h1>
                    <p>
                        <strong>Username:</strong> {userData.properties.username}
                    </p>
                    <p>
                        <strong>Games:</strong> {userData.properties.nGames}
                    </p>
                    <p>
                        <strong>Wins:</strong> {userData.properties.wins}
                    </p>
                    <p>
                        <strong>Losses:</strong> {userData.properties.losses}
                    </p>
                    <p>
                        <strong>Score:</strong> {userData.properties.score}
                    </p>
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
}
