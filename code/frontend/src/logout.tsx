import React, { useContext, useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { AuthContext } from './AuthContext'; // Update with the correct path
import { useCookies } from 'react-cookie';

export function Logout() {
    const context = useContext(AuthContext);
    const [submitting, setSubmitting] = useState(false);
    const [redirect, setRedirect] = useState(false);
    const navigate = useNavigate();
    const [cookies, setCookie, removeCookie] = useCookies(['token']);

    useEffect(() => {
        if (redirect) {
            navigate('/'); // Redirect to the home page or any other desired page
        }
    }, [redirect, navigate]);

    async function handleLogout() {
        setSubmitting(true);

        try {
            const userID = context.id;

            // Check if the user is logged in before attempting to log out
            if (context.loggedin && userID) {
                console.log(userID);
                
                const response = await fetch(`http://localhost:8081/api/users/logout/${userID}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    Accept: 'application/vnd.siren+json',
                    },
                });
console.log(response);
                if (response.ok) {
                    // Clear context values to log the user out
                    context.setLoggedin(() => false);
                    context.setId(() => null);
                    context.setToken(() => null);
                    removeCookie('token');

                    setRedirect(true);
                } else {
                    console.error('Logout failed. Server returned:', response.status, response.statusText);
                    // Handle server error or unauthorized logout
                }
            } else {
                console.warn('User is not logged in. Unable to perform logout.');
                // Handle case where the user is not logged in
            }
        } catch (error) {
            console.error('Logout error:', error);
            // Handle other errors as needed
        } finally {
            setSubmitting(false);
        }
    }

    return (
        <div>
            <button onClick={handleLogout} disabled={submitting}>
                Logout
            </button>
        </div>
    );
}
