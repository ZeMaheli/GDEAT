import React from 'react';
import TextField from '@mui/material/TextField';
import {Tooltip} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import InfoIcon from '@mui/icons-material/Info';

interface CustomTextFieldProps {
    label: string;
    value: string;
    onChange: (value: React.SetStateAction<string>) => void;
    infoText: string;
}

const CustomTextField: React.FC<CustomTextFieldProps> = ({label, value, onChange, infoText}) => {
    return (
        <TextField
            label={
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    {label}
                    <Tooltip title={infoText} arrow>
                        <IconButton size="small" style={{ marginLeft: 8 }}>
                            <InfoIcon fontSize="small" />
                        </IconButton>
                    </Tooltip>
                </div>
            }
            multiline
            rows={10}
            variant="outlined"
            fullWidth
            value={value}
            onChange={(e) => onChange(e.target.value)}
            InputProps={{style: {backgroundColor: '#f0f0f0', color: '#333'}}}
            InputLabelProps={{style: {color: '#333'}}}/>
    );
}

export default CustomTextField;
