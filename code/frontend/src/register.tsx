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

export function Register(): React.ReactElement {
    const [result, setResult] = useState({});
    const [inputs, setInputs] = useState({ username: '', password: '' });
    const [submitting, setSubmitting] = useState(false);
    const [error, setError] = useState('');
    const [redirect, setRedirect] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        if (redirect) {
            navigate('/me');
        }
    }, [redirect, navigate]);

    async function handleSubmit(ev: React.FormEvent<HTMLFormElement>) {
        ev.preventDefault();
        setSubmitting(true);

        try {
            const response = await fetch('http://localhost:8081/api/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Accept: 'application/vnd.siren+json',
                    'Access-Control-Allow-Origin': '*',
                    'no-cors': 'true',
                   
                },
                body: JSON.stringify({
                    username: inputs.username,
                    password: inputs.password,
                }),
            });
            const res = await response.text();
            console.log("ANSWER:"+response.status)
            console.log("ANSWER:"+res);
            if(response.status === 400) {
                console.log("ANSWER:"+res);
                throw new Error('Register failed');
            }
           
            const responseObject = JSON.parse(res);
            const user = responseObject.properties.username;
            console.log("ANSWER:"+user+ "-"+ responseObject.status);
            if(responseObject.status === 400) {
                console.log("ANSWER:"+user+ "-"+ responseObject.status);
                throw new Error('Register failed');
            }
          
            
          
            
           
        } catch (error) {
            setError(error.message);
        } finally {
            setSubmitting(false);
        }
    }

    function handleChange(ev: React.FormEvent<HTMLInputElement>) {
        const name = ev.currentTarget.name;
        setInputs({ ...inputs, [name]: ev.currentTarget.value });
    }

    return (
        <div>
            <Link to="/"> Home </Link>
            <form onSubmit={handleSubmit}>
                <fieldset disabled={submitting}>
                    <div>
                        <label htmlFor="username">Username</label>
                        <input
                            id="username"
                            type="text"
                            name="username"
                            value={inputs.username}
                            onChange={handleChange}
                        />
                    </div>
                    <div>
                        <label htmlFor="password">Password</label>
                        <input
                            id="password"
                            type="password"
                            name="password"
                            value={inputs.password}
                            onChange={handleChange}
                        />
                    </div>
                    <div>
                        <button type="submit">Register</button>
                    </div>
                </fieldset>
            </form>
            {error}
        </div>
    );
}
