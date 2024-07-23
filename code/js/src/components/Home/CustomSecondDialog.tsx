import React from 'react';
import {Dialog, DialogTitle, DialogContent, DialogActions, Button, Typography, Box} from '@mui/material';

interface CustomSecondDialogProps {
    open: boolean;
    onClose: () => void;
    onSelect: (type: 'Entity' | 'Relation') => void;
}

const CustomSecondDialog: React.FC<CustomSecondDialogProps> = ({ open, onClose, onSelect }) => {
    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Item</DialogTitle>
            <DialogContent>
                <Typography>Select the type of item to add:</Typography>
                <Box sx={{display: 'flex', flexDirection: 'column', marginTop: '16px'}}>
                    <Button onClick={() => onSelect('Entity')} color="primary">
                        Entity
                    </Button>
                    <Button onClick={() => onSelect('Relation')} color="secondary">
                        Relation
                    </Button>
                </Box>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
            </DialogActions>
        </Dialog>
    );
};

export default CustomSecondDialog;
