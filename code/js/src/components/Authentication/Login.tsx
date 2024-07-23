import React, {useState} from "react";
import {useSessionManager} from "../../Utils/Session";
import CustomAuthForm from "./CustomAuthForm";
import CustomAuthBox from "./CustomAuthBox";
import {UsersService} from "../../Services/services/users/UsersService";
import {useNavigate} from "react-router-dom";
import {Rels} from "../../Utils/navigation/Rels";
import login = UsersService.login;

export default function Login(): React.ReactElement {
    const navigate = useNavigate()
    const [inputs, setInputs] = useState({username: "", password: ""});
    const [submitting, setSubmitting] = useState(false);
    const [error, setError] = useState('');
    const sessionManager = useSessionManager();

    function handleSubmit(ev: React.FormEvent<HTMLFormElement>) {
        ev.preventDefault();
        setSubmitting(true);
        const {username, password} = inputs;
        login(username, password)
            .then(res => {
                setSubmitting(false);
                if (res) {
                    sessionManager.setSession({username, userHomeLink: Rels.HOME})
                    navigate('/')
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
        setInputs({...inputs, [name]: ev.currentTarget.value});
    }

    return (
        <CustomAuthBox>
            <CustomAuthForm
                onSubmit={handleSubmit}
                isSubmitting={submitting}
                inputs={inputs}
                onChange={handleChange}
                fields={[
                    {id: 'username', name: 'username', label: 'Username', type: 'text'},
                    {id: 'password', name: 'password', label: 'Password', type: 'password'}
                ]}
                submitButtonText="Login"
                alternateActionText="Don't have an account? "
                alternateActionLink="/register"
                alternateActionLinkText="Sign Up"
            />
            {error && <p style={{color: 'red', marginTop: '16px'}}>{error}</p>}
        </CustomAuthBox>
    );
}
