import React, {useEffect, useRef, useState} from 'react';
import {useLoggedIn} from '../Utils/Session';
import '../style/components.css';
//import save from "./utils/save.png";
//import edit from './utils/edit.png';
///import export from './utils/export.png';
//import module from "./utils/default.svg";
import {DiagramsService} from "../Services/services/diagrams/DiagramsServices";
import {CREATE_DIAGRAM} from "../Services/navigation/URIS";
import {Box, Button, TextField} from "@mui/material";
import {instance} from '@viz-js/viz';
import {createDiagramCreateOutputModel} from '../Services/services/diagrams/models/DiagramCreateOutputModel';
import './st.css';
import panzoom from 'panzoom';
import createDiagram = DiagramsService.createDiagram;

export default function Home():React.ReactElement {
    let prompt = "{\"prompt\": \"[Insert Text]\"}";
    const [loggedIn, setLoggedIn] = useState<boolean>(useLoggedIn());
    const [error, setError] = useState<string>('');
    const [dotCode, setDotCode] = useState<string>('');
    const [submitting, setSubmitting] = useState<boolean>(false);
    const [text, setText] = useState<string>("");
    const svgContainerRef = useRef<HTMLDivElement>(null);
    const svgElementRef = useRef<SVGElement | null>(null);

    useEffect(() => {
        if (svgElementRef.current) {
            panzoom(svgElementRef.current, {
                bounds: true,
                boundsPadding: 0.1,
                maxZoom: 3,
                minZoom: 0.5,
            });
        }
    }, [svgElementRef.current]);

    const handleCodeEdit = () => {
        instance().then(viz => {
            let diagramSVG = viz.renderSVGElement(dotCode);
            const container = svgContainerRef.current;
            if (container) {
                container.innerHTML = '';
                container.appendChild(diagramSVG);
                svgElementRef.current = diagramSVG;
                panzoom(svgElementRef.current, {
                    bounds: true,
                    boundsPadding: 0.1,
                    maxZoom: 3,
                    minZoom: 0.5,
                });
            }
        });
    }

    const handleCreateGraph = async () => {
        try {
            setSubmitting(true);
            const response = await createDiagram(CREATE_DIAGRAM, prompt.replace("[Insert Text]", text));
            const prop = response.properties;
            if (prop) {
                const diagramModel = createDiagramCreateOutputModel(prop);
                let graphCode = diagramModel.createNeatoDiagram();
                setDotCode(graphCode)
                instance().then(viz => {
                    let diagramSVG = viz.renderSVGElement(graphCode);
                    setDotCode(graphCode)
                    const container = svgContainerRef.current;
                    if (container) {
                        container.innerHTML = '';
                        container.appendChild(diagramSVG);
                        svgElementRef.current = diagramSVG;
                        panzoom(svgElementRef.current, {
                            bounds: true,
                            boundsPadding: 0.1,
                            maxZoom: 3,
                            minZoom: 0.5,
                        });
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

    function SaveButtonHandler() {
        return undefined;
    }

    return (
        <Box className="container">
            <Box className="left">
                <TextField
                    label="Prompt"
                    multiline
                    rows={10}
                    variant="outlined"
                    fullWidth
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                />
                <Button
                    variant="contained"
                    color="primary"
                    onClick={handleCreateGraph}
                    style={{marginTop: '16px'}}
                    disabled={submitting}
                >
                    Create Graph
                </Button>
            </Box>
            <Box className="right">
                <Box className="top">
                    <TextField
                        label="Dot Code"
                        multiline
                        rows={10}
                        variant="outlined"
                        fullWidth
                        value={dotCode}
                        onChange={(e) => setDotCode(e.target.value)}
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={handleCodeEdit}
                        style={{marginTop: '16px'}}
                        disabled={submitting}
                    >
                        Update Diagram Code
                    </Button>
                </Box>
                <Box className="bottom" ref={svgContainerRef}/>
                {loggedIn &&
                    <Button variant="contained"
                            color="primary"
                            disabled={dotCode == "" }
                            onClick={SaveButtonHandler()}
                            style={{marginTop: '16px'}}
                    >save graph info</Button>
                }
                {loggedIn && <div>loggedIn true</div>}
            </Box>
        </Box>
    );

    /*return (
            <Outlet/>
            {error && <p>{error}</p>}
            <div ref={svgContainerRef}></div>
    );*/
}

