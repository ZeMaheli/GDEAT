import React from 'react';
import {Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField, Typography} from '@mui/material';

interface CustomFifthDialogProps {
    open: boolean;
    onClose: () => void;
    onAddRelation: (firstEntityName: string, relationName: string, secondEntityName: string) => void;
    firstEntityName: string;
    secondEntityName: string;
    relationName: string;
    onFirstEntityNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onSecondEntityChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onRelationNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const CustomFifthDialog: React.FC<CustomFifthDialogProps> =
    ({
         open,
         onClose,
         onAddRelation,
         firstEntityName,
         secondEntityName,
         relationName,
         onFirstEntityNameChange,
         onSecondEntityChange,
         onRelationNameChange
     }) => {
        return (
            <Dialog open={open} onClose={onClose}>
                <DialogTitle>Add Entity</DialogTitle>
                <DialogContent>
                    <Typography>Enter relation name and entities:</Typography>
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
                        <TextField
                            label="Relation Name (#-#)"
                            value={relationName}
                            onChange={onRelationNameChange}
                            fullWidth
                            margin="normal"
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => onAddRelation(firstEntityName, relationName, secondEntityName)}
                            color="primary">
                        Add
                    </Button>
                    <Button onClick={onClose}>Cancel</Button>
                </DialogActions>
            </Dialog>
        );
    };

export default CustomFifthDialog;