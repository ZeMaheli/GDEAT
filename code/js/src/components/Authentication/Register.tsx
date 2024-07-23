import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import CustomAuthBox from "./CustomAuthBox";
import CustomAuthForm from "./CustomAuthForm";
import {UsersService} from "../../Services/services/users/UsersService";
import {Rels} from "../../Utils/navigation/Rels";
import {useSessionManager} from "../../Utils/Session";
import register = UsersService.register;

export default function Register(): React.ReactElement {
    const navigate = useNavigate()
    const [inputs, setInputs] = useState({email: "", username: "", password: ""})
    const [submitting, setSubmitting] = useState(false)
    const [error, setError] = useState('')
    const sessionManager = useSessionManager();

    async function handleSubmit(ev: React.FormEvent<HTMLFormElement>) {
        ev.preventDefault()
        try {
            setSubmitting(true)
            const email = inputs.email
            const username = inputs.username
            const password = inputs.password
            const response = await register(username, email, password)
            const prop = response.properties;
            if (prop) {
                setSubmitting(false)
                sessionManager.setSession({username, userHomeLink: Rels.HOME})
                navigate('/')
            }
        } catch (error) {
            setSubmitting(false)
            setError((error as Error).message)
        }
    }

    function handleChange(ev: React.FormEvent<HTMLInputElement>) {
        const name = ev.currentTarget.name;
        setInputs({...inputs, [name]: ev.currentTarget.value})
    }


    return (
        <CustomAuthBox>
            <CustomAuthForm
                onSubmit={handleSubmit}
                isSubmitting={submitting}
                inputs={inputs}
                onChange={handleChange}
                fields={[
                    {id: 'email', name: 'email', label: 'Email', type: 'text'},
                    {id: 'username', name: 'username', label: 'Username', type: 'text'},
                    {id: 'password', name: 'password', label: 'Password', type: 'password'}
                ]}
                submitButtonText="Register"
                alternateActionText="Already have an account? "
                alternateActionLink="/login"
                alternateActionLinkText="Log In"/>
            {error && <p style={{color: 'red', marginTop: '16px'}}>{error}</p>}
        </CustomAuthBox>
    );
};
