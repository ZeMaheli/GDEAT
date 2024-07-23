import React from 'react';
import {Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField, Typography} from '@mui/material';

interface CustomThirdDialogProps {
    open: boolean;
    onClose: () => void;
    onAddEntity: (name: string, attributes: string) => void;
    entityName: string;
    entityAttributes: string;
    onEntityNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onEntityAttributesChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const CustomThirdDialog: React.FC<CustomThirdDialogProps> =
    ({
         open,
         onClose,
         onAddEntity,
         entityName,
         entityAttributes,
         onEntityNameChange,
         onEntityAttributesChange
     }) => {
        return (
            <Dialog open={open} onClose={onClose}>
                <DialogTitle>Add Entity</DialogTitle>
                <DialogContent>
                    <Typography>Enter entity name and attributes:</Typography>
                    <Box sx={{display: 'flex', flexDirection: 'column', marginTop: '16px'}}>
                        <TextField
                            label="Entity Name"
                            value={entityName}
                            onChange={onEntityNameChange}
                            fullWidth
                            margin="normal"
                        />
                        <TextField
                            label="Attributes (comma-separated)"
                            value={entityAttributes}
                            onChange={onEntityAttributesChange}
                            fullWidth
                            margin="normal"
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => onAddEntity(entityName, entityAttributes)} color="primary">
                        Add
                    </Button>
                    <Button onClick={onClose}>Cancel</Button>
                </DialogActions>
            </Dialog>
        );
    };

export default CustomThirdDialog;
