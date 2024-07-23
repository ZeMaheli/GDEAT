// DiagramDetail.tsx
import React, {useEffect, useRef, useState} from 'react';
import {useParams} from 'react-router-dom';
import {Box, CircularProgress, Typography} from '@mui/material';
import {DiagramsService} from "../../Services/services/diagrams/DiagramsServices";
import {throwError} from "../../Services/utils/errorUtils";
import {GetDiagramOutputModel} from "../../Services/services/diagrams/models/GetDiagramOutputModel";
import {createNeatoDiagram} from "../../Services/services/diagrams/models/DiagramCreateOutputModel";
import {instance} from "@viz-js/viz";
import panzoom from "panzoom";
import CustomTextField from "../Home/CustomTextField";
import getDiagram = DiagramsService.getDiagram;

interface DiagramProps {
}

const Diagram: React.FC<DiagramProps> = () => {
    const {name} = useParams<{ name: string }>();
    const [data, setData] = useState<GetDiagramOutputModel>({entities: {}, relations: {}});
    const [dotCode, setDotCode] = useState<string>('');
    const svgContainerRef = useRef<HTMLDivElement>(null);
    const svgElementRef = useRef<SVGElement | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        const fetchDiagramDetails = async () => {
            try {
                const response = await getDiagram(name ?? throwError("Name not found"));
                const prop = response.properties
                if (prop) {
                    setData(prop);
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
                    })
                }
            } catch (error) {
                throwError("Failed to fetch diagram details:");
            } finally {
                setLoading(false);
            }
        };

        fetchDiagramDetails();
    }, [name]);

    if (loading) {
        return (
            <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh'}}>
                <CircularProgress/>
            </Box>
        );
    }

    if (!data) {
        return (
            <Box sx={{padding: '20px'}}>
                <Typography variant="h6">Diagram not found</Typography>
            </Box>
        );
    }

    return (
        <><Box className="container" sx={{display: 'flex', flexDirection: 'column', gridColumn: '2'}}>
            <CustomTextField label={"Dot Code"} value={dotCode} onChange={setDotCode}
                             infoText={"Plain text diagram description language used to define the structure and attributes of diagrams"}/>
        </Box>
            <Box className="container"
                 sx={{gridColumn: 'span 2', display: 'flex', flexDirection: 'column', marginTop: '16px'}}>
                <Box className="bottom" ref={svgContainerRef} style={{
                    padding: '16px',
                    backgroundColor: '#f0f0f0',
                    overflow: 'hidden',
                    borderTop: '1px solid #ddd',
                    position: 'relative',
                    marginTop: '16px',
                    height: '400px',
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    borderRadius: '8px'
                }}/>
            </Box>
        </>
    );
};

export default Diagram;
