import React from 'react';
import {Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField, Typography} from '@mui/material';

interface CustomFourthDialogProps {
    open: boolean;
    onClose: () => void;
    onRemoveEntity: (name: string) => void;
    entityName: string;
    onEntityNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const CustomFourthDialog: React.FC<CustomFourthDialogProps> =
    ({
         open,
         onClose,
         onRemoveEntity,
         entityName,
         onEntityNameChange,
     }) => {
        return (
            <Dialog open={open} onClose={onClose}>
                <DialogTitle>Add Entity</DialogTitle>
                <DialogContent>
                    <Typography>Enter entity name:</Typography>
                    <Box sx={{display: 'flex', flexDirection: 'column', marginTop: '16px'}}>
                        <TextField
                            label="Entity Name"
                            value={entityName}
                            onChange={onEntityNameChange}
                            fullWidth
                            margin="normal"
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => onRemoveEntity(entityName)} color="primary">
                        Add
                    </Button>
                    <Button onClick={onClose}>Cancel</Button>
                </DialogActions>
            </Dialog>
        );
    };

export default CustomFourthDialog;
