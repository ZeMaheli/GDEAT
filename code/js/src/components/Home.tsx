import React, {useState} from 'react';
import {Link, Outlet} from 'react-router-dom';
import {useLoggedIn} from '../Utils/Session';
import '../style/components.css';
//import save from "./save.png";
//import edit from './utils/edit.png';
///import export from './utils/export.png';
//import module from "./utils/default.svg";
import ImageSVG from './image';
import {DiagramsService} from "../Services/services/diagrams/DiagramsServices";
import createDiagram = DiagramsService.createDiagram;
import {CREATE_DIAGRAM} from "../Services/navigation/URIS";

export default function Home() {
    const [loggedIn, setLoggedIn] = useState<boolean>(useLoggedIn());
    const [error, setError] = useState('');
    const [userId, setUserId] = useState('');
    const [dotCode, setDotCode] = useState('');
    const [byteArray, setByteArray] = useState<Uint8Array>(new Uint8Array());
    const [graphUrl, setGraphUrl] = useState<string | null>(null); // State to store the URL of the SVG image
    const [submitting, setSubmitting] = useState(false);
    const [prompt, setPrompt] = useState("{" +
        "\"prompt\": \"Pretendemos guardar informação sobre os profissionais de saúde, doentes e sessões de um centro de fisioterapia. Cada doente é identificado pelo seu CC. Cada profissional de saúde é identificado pela sua cédula profissional (CP). As sessões dizem respeito a um profissional de saúde e um doente. Cada sessão ´e identificada por um número de ordem (NO), que é único para cada doente (e.g. existem as sessões 1, 2 e 3 do doente Manuel, e as sessões 1, 2 e 3 da doente Maria).\"}");

    const [content, setContent] = useState('');

    const handleFileRead = (e: React.ChangeEvent<HTMLInputElement>) => {
        setContent("");
        const g = e.target;
        const file = g.files ? g.files[0] : null;
        if (!file) {
            return;
        }

        const reader = new FileReader();
        reader.onload = (event: ProgressEvent<FileReader>) => {
            const target = event.target;
            if (target && target.result) {
                setContent(text => text + target.result as string);
            }
        };
        reader.readAsText(file);
    };

    const handleCreateGraph = async () => {
        try {
            setSubmitting(true);
            const response = await createDiagram(CREATE_DIAGRAM, "{ \n \"prompt\": \"" + content.replace(/\r\n/g, '') + "\" \n}");
            const prop = response.properties;
            if (prop) {
                const base64String = prop.diagramPDF; // Assuming prop.diagramPDF is the Base64 string

                // Log the Base64 string to debug
                console.log('Base64 String:', base64String);

                // Decode the Base64 string
                const binaryString = window.atob(base64String.toString());

                // Convert binary string to a byte array
                const len = binaryString.length;
                const bytes = new Uint8Array(len);
                for (let i = 0; i < len; i++) {
                    bytes[i] = binaryString.charCodeAt(i);
                }

                // Create a Blob from the byte array
                const blob = new Blob([bytes], { type: 'image/svg+xml' });

                // Create an object URL for the Blob
                const url = URL.createObjectURL(blob);

                // Log the URL to debug
                console.log('Generated SVG URL:', url);

                // Store the URL in the state
                setGraphUrl(url);

                setSubmitting(false);
            }
        } catch (error) {
            console.error('Graph creation failed', error);
            setError((error as Error).message);
            setSubmitting(false);
        }
    };

    return (
        <div className="containerStyle">
            <div className="headerContainer">
                <Link to="/" className="headerStyle">Welcome to GDEAT</Link>
            </div>
            {loggedIn ? (
                <div>
                    <div className="playContainerStyle">
                        <button onClick={handleCreateGraph} className="playStyle" disabled={submitting}>
                            Generate Graph
                        </button>
                    </div>
                </div>
            ) : (
                <div>
                    <div className="navigationContainerStyle">
                        <div>
                            <label>Choose file to upload</label>
                            <input type="file" id="file" name="file" accept='.txt' onChange={handleFileRead}/>
                            <p>{content}</p>
                        </div>
                        <div>
                            <textarea value={dotCode} onChange={e => setDotCode(e.target.value)} cols={60} rows={40}/>
                            <Link to="/graphs/save" className="linkStyle">
                                save
                            </Link>
                        </div>
                        <div>
                            {/* Ensure ImageSVG is defined or import correctly */}
                            <ImageSVG/>
                            <Link to="/graphs/export" className="linkStyle">
                                Export
                            </Link>
                            <Link to="/graphs/edit" className="linkStyle">
                                edit
                            </Link>
                        </div>
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
                    <img src={graphUrl} alt="Generated Graph"/>
                </div>
            )}
        </div>
    );
}

