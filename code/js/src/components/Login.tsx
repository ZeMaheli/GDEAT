import { Link, Navigate } from "react-router-dom";
import React, { useState } from "react";
import { login } from "../Services/usersServices";
import {
    containerStyle,
    credentialsLinkStyle,
    errorStyle,
    formStyle,
    homeLinkStyle,
    inputContainerStyle,
    inputStyle
} from "../authentication/style/authenticationBoxStyle";

export default function Login(): React.ReactElement {
    const [inputs, setInputs] = useState({ username: "", password: "" })
    const [submitting, setSubmitting] = useState(false)
    const [error, setError] = useState('')
    const [redirect, setRedirect] = useState(false)

    if (redirect) return <Navigate to="/" replace={true} />;

    function handleSubmit(ev: React.FormEvent<HTMLFormElement>) {
        ev.preventDefault()
        setSubmitting(true)
        const username = inputs.username
        const password = inputs.password
        login(username, password)
            .then(res => {
                //setSession(res.properties.accessToken);
                setSubmitting(false)
                if (res) {
                    setRedirect(true)
                } else {
                    setError("Invalid email or password")
                }
            })
            .catch(error => {
                setSubmitting(false)
                setError(error.message)
            })
    }

    function handleChange(ev: React.FormEvent<HTMLInputElement>) {
        const name = ev.currentTarget.name;
        setInputs({ ...inputs, [name]: ev.currentTarget.value });
    }

    return (
        <><div />
            <div style={containerStyle}>
                <Link className={"home-redirect"} to="/" style={homeLinkStyle}>Home</Link>
                <form onSubmit={handleSubmit} style={formStyle}>
                    <fieldset disabled={submitting}>
                        <div style={inputContainerStyle}>
                            <label htmlFor="username">username</label>
                            <input
                                id="username"
                                name="username"
                                onChange={handleChange}
                                style={inputStyle} />
                        </div>
                        <div style={inputContainerStyle}>
                            <label htmlFor="password">Password</label>
                            <input
                                id="password"
                                type="password"
                                name="password"
                                value={inputs.password}
                                onChange={handleChange}
                                style={inputStyle} />
                        </div>
                        <div>
                            <button type="submit" style={submitButtonStyle}>
                                {submitting ? 'Logging in...' : 'Login'}
                            </button>
                        </div>
                        <p style={credentialsLinkStyle}>
                            Don't have an account? <Link id={"sign-up-redirect"} to="/register">Sign Up</Link>
                        </p>
                    </fieldset>
                </form>
                {error && <p style={errorStyle}>{error}</p>}
            </div>
        </>
    );
};

const submitButtonStyle: React.CSSProperties = {
    marginTop: '10px',
    width: '100%',
    padding: '10px',
    borderRadius: '5px',
    border: 'none',
    backgroundColor: '#0048ff',
    color: 'white',
    cursor: 'pointer',
};
