import React, { createContext, useEffect, useState, useContext } from 'react';
import { Logout } from './logout';
import { useCookies } from 'react-cookie';

import { AuthContext } from './AuthContext';
import { createRoot } from 'react-dom/client';
import {
    createBrowserRouter,
    RouterProvider,
    Link,
    useNavigate,
} from 'react-router-dom';


export function Login() {//: React.ReactElement {
   const [cookies, setCookie, removeCookie] = useCookies(['token']);
   const context = useContext(AuthContext);
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
            const response = await fetch('http://localhost:8081/api/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Accept: 'application/vnd.siren+json',
                   

                },
                body: JSON.stringify({
                    username: inputs.username,
                    password: inputs.password,
                }),
            });
            if(!response.ok) {
                throw new Error('Login failed');
            }
            const res = await response.text();
            // Converter a string JSON para um objeto JavaScript
            const responseObject = JSON.parse(res);


            const id: number = responseObject.properties.id;
            const token: string = responseObject.properties.token;
            //guardar id e token no context para ser visivel em todos os campos do codigos

            context.setLoggedin(() => true);
            context.setId(() => id);
            context.setToken(() => token);
            //passar token no cookie
            setCookie('token', 'bearer ' + token);

            // Imprimir as propriedades
            console.log('ID:', id);
            console.log('Token:', token);


            // Trate o resultado conforme necessário
            if (id != null && token != null) {
                setRedirect(true);
            } else {
                setError('Cannot Login');
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
                        <button type="submit">Login</button>
                    </div>
                </fieldset>
            </form>
            {error}
        </div>
    );
}