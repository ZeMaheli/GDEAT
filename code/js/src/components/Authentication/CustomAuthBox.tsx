import {Box} from "@mui/material";
import {Link} from "react-router-dom";
import React from "react";

interface CustomAuthBoxProps {
    children: React.ReactNode;
    error?: string;
}

const CustomAuthBox: React.FC<CustomAuthBoxProps> = ({children}) => {
    return (
        <Box sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            gridColumn: '1 / span 2',
            justifyContent: 'center'
        }}>
            <Box sx={{
                width: '100%', maxWidth: 400, display: 'flex', flexDirection: 'column', alignItems: 'center',
                backgroundColor: '#f5f5f5',
                padding: '16px',
                borderRadius: '8px'
            }}>
                <Link className="home-redirect" to="/" style={{
                    marginBottom: '16px',
                    color: '#00796b',
                    textDecoration: 'none',
                    alignSelf: 'center',
                    fontSize: '16px',
                    fontWeight: '500'
                }}>Home</Link>
                {children}
            </Box>
        </Box>
    )
}

export default CustomAuthBox