import React, {useRef, useState} from 'react';
import {Link, Outlet} from 'react-router-dom';
import {useLoggedIn} from '../Utils/Session';
import '../style/components.css';
//import save from "./save.png";
//import edit from './utils/edit.png';
///import export from './utils/export.png';
//import module from "./utils/default.svg";
import {DiagramsService} from "../Services/services/diagrams/DiagramsServices";
import {CREATE_DIAGRAM} from "../Services/navigation/URIS";
import {saveAs} from "file-saver"
import {Button} from "@mui/material";
import {instance} from '@viz-js/viz';
import {createDiagramCreateOutputModel} from '../Services/services/diagrams/models/DiagramCreateOutputModel';
import createDiagram = DiagramsService.createDiagram;


export default function Home() {
    const [loggedIn, setLoggedIn] = useState<boolean>(useLoggedIn());
    const [error, setError] = useState<string>('');
    const [dotCode, setDotCode] = useState<string>('');
    const [graphUrl, setGraphUrl] = useState<string | null>(null); // State to store the URL of the SVG image
    const [submitting, setSubmitting] = useState<boolean>(false);
    const [prompt, setPrompt] = useState<string>("{" +
        "\"prompt\": \"Pretendemos guardar informação sobre os profissionais de saúde, doentes e sessões de um centro de fisioterapia. Cada doente é identificado pelo seu CC. Cada profissional de saúde é identificado pela sua cédula profissional (CP). As sessões dizem respeito a um profissional de saúde e um doente. Cada sessão ´e identificada por um número de ordem (NO), que é único para cada doente (e.g. existem as sessões 1, 2 e 3 do doente Manuel, e as sessões 1, 2 e 3 da doente Maria).\"}");

    const [content, setContent] = useState<string>('');
    const svgContainerRef = useRef<HTMLDivElement>(null); // Ref to hold the SVG container

    const Index = () => {
        const downloadImage = () => {
            if (graphUrl != null)
                saveAs(graphUrl, 'image.svg') // Put your image URL here.
        }

        return <Button onClick={downloadImage}>Export</Button>
    }

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
            const response = await createDiagram(CREATE_DIAGRAM, prompt);
            const prop = response.properties;
            if (prop) {
                const diagramModel = createDiagramCreateOutputModel(prop);
                let graphCode = diagramModel.createNeatoDiagram();

                instance().then(viz => {
                    let diagramSVG = viz.renderSVGElement(graphCode);

                    const container = svgContainerRef.current;
                    if (container) {
                        container.innerHTML = ''; // Clear previous SVG if any
                        container.appendChild(diagramSVG); // Append the new SVG
                    }

                    setSubmitting(false);
                });
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
            <div ref={svgContainerRef}></div>
        </div>
    );
}

