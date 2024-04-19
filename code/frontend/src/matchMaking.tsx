import React, { createContext, useEffect, useState, useContext } from 'react';
import { Logout } from './logout';
import { CookiesProvider } from 'react-cookie';
import { useCookies } from 'react-cookie';

import { AuthContext } from './AuthContext';
import { createRoot } from 'react-dom/client';
import {
    createBrowserRouter,
    RouterProvider,
    Link,
    useNavigate,
} from 'react-router-dom';


export function Matchmaking(): React.ReactElement {
    const authData = useContext(AuthContext);
    const [cookies, setCookie, removeCookie] = useCookies(['token']);
    const [openingRule, setOpeningRule] = useState("");
    const [variante, setVariante] = useState("");
    const [submitting, setSubmitting] = useState(false);
    const [error, setError] = useState('');
    const [redirect, setRedirect] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        let pollingInterval;

        async function repeatRequest() {
            try {
                const response = await fetch('http://localhost:8081/api/games/matchMaking', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        Accept: 'application/vnd.siren+json',
                        authorization: 'Bearer ' + authData.token,
                    },
                    body: JSON.stringify({
                        player: authData.id,
                        openingRule: openingRule,
                        variante: variante,
                    }),
                });
          //      console.log(cookies)
                if (!response.ok) {
                    throw new Error('fill game modes');
                }

                const res = await response.text();
                const responseObject = JSON.parse(res);

                const props = responseObject.properties;
                const gameId = props.gameID;
                console.log(cookies)

                if (gameId != null) {
                    authData.gameID = gameId;
                    setRedirect(true);
                    navigate('/board');
                    clearInterval(pollingInterval); // Stop polling when game is found
                }
            } catch (error) {
                setError(error.message);
            }
        }

        if (redirect) {
            navigate('/me');
        } else {
            // Fetch initial data on component mount
            repeatRequest();
            pollingInterval = setInterval(repeatRequest, 10000);

            // Cleanup function to clear the interval when the component is unmounted
            return () => clearInterval(pollingInterval);
        }
    }, [redirect, navigate, authData.id, authData.token, openingRule, variante]);

    function handleSubmit(ev: React.FormEvent<HTMLFormElement>) {
        ev.preventDefault();
        setSubmitting(true);
    }


    function handleChangeVariante(ev: React.FormEvent<HTMLInputElement>) {
        const name = ev.currentTarget.name;
        setVariante(ev.currentTarget.value);//{ ...openingRule, [name]: ev.currentTarget.value });
    }
    function handleChangeOpeningRule(ev: React.FormEvent<HTMLInputElement>) {
        const name = ev.currentTarget.name;
        setOpeningRule(ev.currentTarget.value);//{ ...openingRule, [name]: ev.currentTarget.value });
    }

    return (
        <div>
            <Link to="/">Home</Link>
            <form onSubmit={handleSubmit}>
                <fieldset disabled={submitting}>
                    <div>
                        <label htmlFor="openingRule">OpeningRule</label>
                        <input
                            id="openingRule"
                            type="text"
                            name="openingRule"
                            value={openingRule}
                            onChange={handleChangeOpeningRule}
                        />
                    </div>

                    <div>
                        <label htmlFor="variante">Variante</label>
                        <input
                            id="Variante"
                            type="text"
                            name="Variante"
                            value={variante}
                            onChange={handleChangeVariante}
                        />
                    </div>
                    <div>
                        <button type="submit">Start Matchmaking</button>
                    </div>
                    {error && <div>{error}</div>}
                </fieldset>
            </form>
            {error && <div>{error}</div>}
        </div>
    );
}