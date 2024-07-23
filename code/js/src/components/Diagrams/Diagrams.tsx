import React, {useEffect, useState} from "react";
import {Box} from "@mui/material";
import CustomDiagramItem from "./CustomDiagramItem";
import {DiagramsService} from "../../Services/services/diagrams/DiagramsServices";
import {GetDiagramsOutputModel} from "../../Services/services/diagrams/models/GetDiagramsOutputModel";
import {throwError} from "../../Services/utils/errorUtils";
import {useNavigate} from "react-router-dom";
import getDiagrams = DiagramsService.getDiagrams;

export default function Diagrams(): React.ReactElement {
    const navigate = useNavigate()
    const [diagrams, setDiagrams] = useState<string[]>([]);

    useEffect(() => {
        const fetchDiagrams = async () => {
            const response = await getDiagrams();
            const newList = diagrams
            response.getEmbeddedSubEntities<GetDiagramsOutputModel>().forEach(diagram => {
                const diagramName = diagram.properties?.name ?? throwError("Name not found")
                newList.push(diagramName);
            });
            setDiagrams(newList);
        };

        fetchDiagrams();
    }, []);

    const handleDiagramClick = async (diagramName: string) => {
        navigate(`/diagrams/${diagramName}`);
    };

    return (
        <Box className="container" sx={{display: 'flex', flexDirection: 'column', gridColumn: '1'}}>
            <h1>Diagrams List</h1>
            {diagrams.map((diagram) => (
                <CustomDiagramItem
                    name={diagram}
                    onClick={handleDiagramClick}
                />
            ))}
        </Box>
    );
};