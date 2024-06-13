import {Link, Navigate} from "react-router-dom";
import React, {useState} from "react";
import {register} from "../Services/usersServices";
import {
    credentialsLinkStyle,
    errorStyle,
    formContainerStyle,
    formStyle,
    homeLinkStyle,
    inputContainerStyle,
    inputStyle,
    labelStyle,
    outerContainerStyle,
    submitButtonStyle
} from "./style/authenticationBoxStyle";

export default function Register(): React.ReactElement {
    const [inputs, setInputs] = useState({email: "", username: "", password: ""})
    const [submitting, setSubmitting] = useState(false)
    const [error, setError] = useState('')
    const [redirect, setRedirect] = useState(false)

    if (redirect) return <Navigate to="/" replace={true}/>;

    function handleSubmit(ev: React.FormEvent<HTMLFormElement>) {
        ev.preventDefault()
        setSubmitting(true)
        const email = inputs.email
        const username = inputs.username
        const password = inputs.password
        register(username, email, password)
            .then(res => {
                //setSession(res.properties.accessToken);
                setSubmitting(false)
                if (res) {
                    setRedirect(true)
                } else {
                    setError("Invalid email, username or password")
                }
            })
            .catch(error => {
                setSubmitting(false)
                setError(error.message)
            })
    }

    function handleChange(ev: React.FormEvent<HTMLInputElement>) {
        const name = ev.currentTarget.name;
        setInputs({...inputs, [name]: ev.currentTarget.value})
    }


    return (
        <div style={outerContainerStyle}>
            <div style={formContainerStyle}>
                <Link className="home-redirect" to="/" style={homeLinkStyle}>Home</Link>
                <form onSubmit={handleSubmit} style={formStyle}>
                    <fieldset disabled={submitting} style={{ border: 'none' }}>
                        <div style={inputContainerStyle}>
                            <label htmlFor="email" style={labelStyle}>Email</label>
                            <input
                                id="email"
                                type="text"
                                name="email"
                                value={inputs.email}
                                onChange={handleChange}
                                style={inputStyle}
                            />
                        </div>
                        <div style={inputContainerStyle}>
                            <label htmlFor="username" style={labelStyle}>Username</label>
                            <input
                                id="username"
                                type="text"
                                name="username"
                                value={inputs.username}
                                onChange={handleChange}
                                style={inputStyle}
                            />
                        </div>
                        <div style={inputContainerStyle}>
                            <label htmlFor="password" style={labelStyle}>Password</label>
                            <input
                                id="password"
                                type="password"
                                name="password"
                                value={inputs.password}
                                onChange={handleChange}
                                style={inputStyle}
                            />
                        </div>
                        <div>
                            <button type="submit" style={submitButtonStyle}>
                                {submitting ? 'Registering...' : 'Register'}
                            </button>
                        </div>
                        <p style={credentialsLinkStyle}>
                            Already have an account? <Link id="login-redirect" to="/login" style={{ color: '#00A67E', textDecoration: 'none' }}>Log In</Link>
                        </p>
                    </fieldset>
                </form>
                {error && <p style={errorStyle}>{error}</p>}
            </div>
        </div>
    );
};
