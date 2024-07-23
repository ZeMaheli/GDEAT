import React from "react";
import {Box} from "@mui/material";

const CustomDiagramItem: React.FC<{
    name: string,
    onClick: (id: string
    ) => void
}> = ({name, onClick}) => {
    return (
        <Box
            className="diagram-item"
            sx={{
                display: 'flex',
                flexDirection: 'column',
                padding: '16px',
                border: '1px solid #ccc',
                marginBottom: '8px',
                cursor: 'pointer'
            }}
            onClick={() => onClick(name)}
        >
            <h2>{name}</h2>
        </Box>
    );
};

export default CustomDiagramItem