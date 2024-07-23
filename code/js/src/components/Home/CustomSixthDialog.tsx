import React from 'react';
import {Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField, Typography} from '@mui/material';

interface CustomSixthDialogProps {
    open: boolean;
    onClose: () => void;
    onRemoveRelation: (firstEntityName: string, secondEntityName: string) => void;
    firstEntityName: string;
    secondEntityName: string;
    onFirstEntityNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onSecondEntityChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const CustomSixthDialog: React.FC<CustomSixthDialogProps> =
    ({
         open,
         onClose,
         onRemoveRelation,
         firstEntityName,
         secondEntityName,
         onFirstEntityNameChange,
         onSecondEntityChange,
     }) => {
        return (
            <Dialog open={open} onClose={onClose}>
                <DialogTitle>Remove Entity</DialogTitle>
                <DialogContent>
                    <Typography>Enter entities:</Typography>
                    <Box sx={{display: 'flex', flexDirection: 'column', marginTop: '16px'}}>
                        <TextField
                            label="First Entity Name"
                            value={firstEntityName}
                            onChange={onFirstEntityNameChange}
                            fullWidth
                            margin="normal"
                        />
                        <TextField
                            label="Second Entity Name"
                            value={secondEntityName}
                            onChange={onSecondEntityChange}
                            fullWidth
                            margin="normal"
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => onRemoveRelation(firstEntityName, secondEntityName)}
                            color="primary">
                        Add
                    </Button>
                    <Button onClick={onClose}>Cancel</Button>
                </DialogActions>
            </Dialog>
        );
    };

export default CustomSixthDialog;