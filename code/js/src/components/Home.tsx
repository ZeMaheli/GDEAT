import React, {useState} from 'react';
import {Link, Outlet} from 'react-router-dom';
import {clearSession, isLogged} from '../authentication/Session';
import '../style/components.css';
import {logout} from "../Services/usersServices";
//import save from "./save.png";
//import edit from './utils/edit.png';
///import export from './utils/export.png';
//import module from "./utils/default.svg";
import {createGraph} from '../Services/gamesServices';

export default function Home() {
    const [loggedIn, setLoggedIn] = useState<boolean>(isLogged());
    const [error, setError] = useState('');
    const [userId, setUserId] = useState('');
    const [dotCode, setDotCode] = useState('');
    const [byteArray, setByteArray] = useState<Uint8Array>(new Uint8Array());
    const [graphUrl, setGraphUrl] = useState<string | null>(null); // State to store the URL of the SVG image
    const [submitting, setSubmitting] = useState(false);
    const [prompt, setPrompt] = useState("{" +
        "\"prompt\": \"Pretendemos guardar informação sobre os profissionais de saúde, doentes e sessões de um centro de fisioterapia. Cada doente é identificado pelo seu CC. Cada profissional de saúde é identificado pela sua cédula profissional (CP). As sessões dizem respeito a um profissional de saúde e um doente. Cada sessão ´e identificada por um número de ordem (NO), que é único para cada doente (e.g. existem as sessões 1, 2 e 3 do doente Manuel, e as sessões 1, 2 e 3 da doente Maria).\"}");

    const handleLogout = async (ev: React.FormEvent<HTMLFormElement>): Promise<boolean> => {
        ev.preventDefault();
        try {
            const data = await logout();
            console.log('Logout successful', data);
            setLoggedIn(false);
            clearSession();
            return true;
        } catch (error) {
            console.error('Logout failed', error);
            setError((error as Error).message);
            throw error;
        }
    };

    const handleCreateGraph = async () => {
        try {
            setSubmitting(true);
            const response = await createGraph(prompt)
            console.log('Graph created', response.data);
            const arrayBuffer = await response.properties.diagramPDFs;
            // Assuming the byte array is in response.data
            const byteArray = new Uint8Array(arrayBuffer);
            setByteArray(byteArray);

            // Create a Blob from the byte array
            const blob = new Blob([byteArray], {type: 'image/svg+xml'});

            // Create an object URL for the Blob
            const url = URL.createObjectURL(blob);

            // Store the URL in the state
            setGraphUrl(url);

            setSubmitting(false);
        } catch (error) {
            console.error('Graph creation failed', error);
            setError((error as Error).message);
            setSubmitting(false);
        }
    };

    return (
        <div className="containerStyle">
            <div className="headerContainer">
                <Link to="" className="headerStyle">Welcome to GDEAT</Link>
            </div>
            {loggedIn ? (
                <div>
                    <div className="navigationContainerStyle">
                        <>
                            <Link to="/graphs" className="linkStyle">
                                List of Graphs
                            </Link>
                            <Link to={`/users/${userId}`} className="linkStyle">
                                Profile
                            </Link>
                            <Link to="/about" className="linkStyle">
                                About
                            </Link>
                            <form onSubmit={handleLogout}>
                                <button className="button-24" role="button">Logout</button>
                            </form>
                        </>
                    </div>
                    <div className="playContainerStyle">
                        <button onClick={handleCreateGraph} className="playStyle" disabled={submitting}>
                            Generate Graph
                        </button>
                    </div>
                </div>
            ) : (
                <div>
                    <div className="navigationContainerStyle">
                        <>
                            <form method="post" encType="multipart/form-data">
                                <div>
                                    <label>Choose file to upload</label>
                                    <input type="file" id="file" name="file" accept='.txt'/>
                                </div>
                                <div>
                                    <button>Submit</button>
                                </div>
                            </form>
                            <div>
                                <textarea value={dotCode} onChange={e => setDotCode(e.target.value)} cols={60}
                                          rows={40}/>
                                <Link to="/graphs/save" className="linkStyle">
                                    save
                                </Link>
                            </div>
                            <Link to="/users/login" className="linkStyle">
                                Login
                            </Link>
                            <Link to="/users/register" className="linkStyle">
                                Register
                            </Link>
                            <Link to="/graphs" className="linkStyle">
                                Gallery
                            </Link>
                            <Link to="/about" className="linkStyle">
                                about
                            </Link>
                            <div>
                                <Link to="/graphs/export" className="linkStyle">
                                    Export
                                </Link>
                                <Link to="/graphs/edit" className="linkStyle">
                                    edit
                                </Link>
                            </div>
                        </>
                    </div>
                    <div className="navigationContainerStyle">
                        <h1 className="loginOrRegister">↑ Login or Register to Create Graph! ↑</h1>
                    </div>
                    <div className="playContainerStyle">
                        <button onClick={handleCreateGraph} className="playStyle" disabled={submitting}>
                            Generate Graph
                        </button>
                    </div>
                </div>
            )}
            <Outlet/>
            {error && <p>{error}</p>}
            {graphUrl && (
                <div>
                    <h2>Generated Graph</h2>
                    {/* Display the SVG image */}
                    <img src={graphUrl} alt="Generated Graph"/>
                </div>
            )}
        </div>
    );
}
