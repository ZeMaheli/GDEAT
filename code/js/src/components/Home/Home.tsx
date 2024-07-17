import React, {useEffect, useRef, useState} from 'react';
//import save from "./utils/save.png";
//import edit from './utils/edit.png';
///import export from './utils/export.png';
//import module from "./utils/default.svg";
import {DiagramsService} from "../../Services/services/diagrams/DiagramsServices";
import {CREATE_DIAGRAM} from "../../Services/navigation/URIS";
import {Box} from "@mui/material";
import {instance} from '@viz-js/viz';
import {
    createNeatoDiagram,
    DiagramCreateOutputModel
} from '../../Services/services/diagrams/models/DiagramCreateOutputModel';
import '../st.css';
import panzoom from 'panzoom';
import CustomButton from "./CustomButton";
import CustomTextField from "./CustomTextField";
import createDiagram = DiagramsService.createDiagram;

export default function Home(): React.ReactElement {
    let prompTextPlaceholder = "[Insert Text]"
    let prompt = `{"prompt": "${prompTextPlaceholder}"}`;
    const [error, setError] = useState<string>('');
    const [text, setText] = useState<string>("");
    const [submitting, setSubmitting] = useState<boolean>(false);
    const [dotCode, setDotCode] = useState<string>('');
    const [data, setData] = useState<DiagramCreateOutputModel>({entities: {}, relations: {}});
    const svgContainerRef = useRef<HTMLDivElement>(null);
    const svgElementRef = useRef<SVGElement | null>(null);
    const [menuAnchorEl, setMenuAnchorEl] = useState<null | HTMLElement>(null);
    const [subMenuAnchorEl, setSubMenuAnchorEl] = useState<null | HTMLElement>(null);
    const [mainMenuOption, setMainMenuOption] = useState<string | null>(null);

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

    const addEntity = (entity: string, entityData: any) => {
        setData(prevData => ({
            ...prevData,
            entities: {
                ...prevData.entities,
                [entity]: entityData
            }
        }));
    }

    const removeEntity = (entity: string) => {
        if (data.entities[entity]) {
            var newMap = data
            delete newMap.entities[entity]
            setData(newMap)
        }
    }

    const addRelation = (entity1: string, relation: string, entity2: string) => {
        var newMap = data
        newMap.relations[entity1][entity2] = relation
        setData(newMap)
    }

    const removeRelation = (entity1: string, relation: string, entity2: string) => {
        if (data.entities[entity1] && data.entities[entity2]) {
            var newMap = data
            delete newMap.relations[entity1][entity2]
            setData(newMap)
        }
    }

    const addAttribute = (entity: string, attribute: string) => {
        if (data.entities[entity]) {
            var newMap = data
            newMap.entities[entity].push(attribute)
            setData(newMap)
        }
    }

    const removeAttribute = (entity: string, attribute: string) => {
        if (data.entities[entity]) {
            var newMap = data
            var index = newMap.entities[entity].indexOf(attribute)
            if (index > -1) {
                newMap.entities[entity].splice(index, 1);
            }
            setData(newMap)
        }
    }

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
            const response = await createDiagram(CREATE_DIAGRAM, prompt.replace(prompTextPlaceholder, text));
            const prop = response.properties;
            if (prop) {
                const graphCode = createNeatoDiagram(prop);
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

    const handleMenuClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setMenuAnchorEl(event.currentTarget);
    };

    const handleMenuClose = () => {
        setMenuAnchorEl(null);
        setSubMenuAnchorEl(null);
        setMainMenuOption(null);
    };

    const handleMainMenuSelect = (option: string) => (event: React.MouseEvent<HTMLElement>) => {
        setMainMenuOption(option);
        setSubMenuAnchorEl(event.currentTarget);
    };

    const handleSubMenuSelect = (option: string) => {
        console.log(`Selected ${mainMenuOption} - ${option}`);
        handleMenuClose();
    };

    return (
        <>
            <Box className="container" sx={{display: 'flex', flexDirection: 'column', gridColumn: '1'}}>
                <CustomTextField label={"Prompt"} value={text} onChange={setText} infoText={"Textual description of the problem"}/>
                <CustomButton
                    label="Create Diagram"
                    onClick={handleCreateGraph}
                    disabled={submitting}
                />
            </Box>
            <Box className="container" sx={{display: 'flex', flexDirection: 'column', gridColumn: '2'}}>
                <CustomTextField label={"Dot Code"} value={dotCode} onChange={setDotCode} infoText={"Plain text diagram description language used to define the structure and attributes of diagrams"}/>
                <CustomButton
                    label="Update Code"
                    onClick={handleCodeEdit}
                    disabled={submitting || dotCode.length === 0}
                />
            </Box>
            <Box className="container"
                 sx={{gridColumn: 'span 2', display: 'flex', flexDirection: 'column', marginTop: '16px'}}>
                <Box className="bottom" ref={svgContainerRef}/>
                <Box sx={{display: 'flex', justifyContent: 'center', marginTop: '16px'}}>
                    <CustomButton
                        label="Save Diagram"
                        onClick={handleMenuClick}
                        disabled={submitting || (dotCode.length === 0 && svgContainerRef.current === null)}
                    />
                    <CustomButton
                        label="Update Diagram"
                        onClick={handleMenuClick}
                        disabled={submitting || dotCode.length === 0}
                    />
                    <CustomButton
                        label="Export Diagram"
                        onClick={handleMenuClick}
                        disabled={submitting || dotCode.length === 0}
                    />
                </Box>
            </Box></>
    );
}

