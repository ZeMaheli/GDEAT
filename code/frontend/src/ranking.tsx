import React, { createContext, useEffect, useState, useContext } from 'react';
import { Logout } from './logout';

import { AuthContext } from './AuthContext';
import { createRoot } from 'react-dom/client';
import {
    createBrowserRouter,
    RouterProvider,
    Link,
    useNavigate,
} from 'react-router-dom';


export function Ranking() {
    const [rankingData, setRankingData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function fetchRankingData() {
            try {
                const response = await fetch('http://localhost:8081/api/ranking', {
                    headers: {
                        Accept: 'application/json',
                        // Add any other headers as needed
                    },
                });

                if (!response.ok) {
                    throw new Error('Failed to fetch ranking data');
                }

                const data = await response.json();
                setRankingData(data);
            } catch (error) {
                console.error('Error fetching ranking data:', error.message);
            } finally {
                setLoading(false);
            }
        }

        fetchRankingData();
    }, []); // Empty dependency array ensures the effect runs only once on mount

    return (
        <div>
            <h1>Ranking</h1>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <ul>
                    {rankingData.map((item) => (
                        <li key={item.id}>
                            {`${item.ranking} - ${item.username}: Vitórias = ${item.vitorias}, Jogos: ${item.jogos}`}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}