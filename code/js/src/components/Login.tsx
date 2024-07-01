import {Link, Navigate} from "react-router-dom";
import React, {useState} from "react";
import {login} from "../Services/usersServices";
import {useSessionManager} from "../Utils/Session";
import {
    credentialsLinkStyle, errorStyle,
    formContainerStyle,
    formStyle,
    homeLinkStyle, inputContainerStyle, inputStyle, labelStyle,
    outerContainerStyle, submitButtonStyle
} from "./style/authenticationBoxStyle";

export default function Login(): React.ReactElement {
    const [inputs, setInputs] = useState({ username: "", password: "" });
    const [submitting, setSubmitting] = useState(false);
    const [error, setError] = useState('');
    const [redirect, setRedirect] = useState(false);
    const { session,setSession ,clearSession} = useSessionManager();
    if (redirect) return <Navigate to="/" replace={true} />;

    function handleSubmit(ev: React.FormEvent<HTMLFormElement>) {
        ev.preventDefault();
        setSubmitting(true);
        const { username, password } = inputs;
        login(username, password)
            .then(res => {

                setSubmitting(false);
                if (res) {
                    setSession(res.properties.accessToken);
                    setRedirect(true);
                } else {
                    setError("Invalid username or password");
                }
            })
            .catch(error => {
                setSubmitting(false);
                setError(error.message);
            });
    }

    function handleChange(ev: React.FormEvent<HTMLInputElement>) {
        const name = ev.currentTarget.name;
        setInputs({ ...inputs, [name]: ev.currentTarget.value });
    }

    return (
        <div style={outerContainerStyle}>
            <div style={formContainerStyle}>
                <Link className="home-redirect" to="/" style={homeLinkStyle}>Home</Link>
                <form onSubmit={handleSubmit} style={formStyle}>
                    <fieldset disabled={submitting} style={{ border: 'none' }}>
                        <div style={inputContainerStyle}>
                            <label htmlFor="username" style={labelStyle}>username</label>
                            <input
                                id="username"
                                name="username"
                                onChange={handleChange}
                                value={inputs.username}
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
                                {submitting ? 'Logging in...' : 'Login'}
                            </button>
                        </div>
                        <p style={credentialsLinkStyle}>
                            Don't have an account? <Link id="sign-up-redirect" to="/register" style={{ color: '#00A67E', textDecoration: 'none' }}>Sign Up</Link>
                        </p>
                    </fieldset>
                </form>
                {error && <p style={errorStyle}>{error}</p>}
            </div>
        </div>
    );
}
