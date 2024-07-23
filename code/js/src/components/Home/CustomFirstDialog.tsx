import React from 'react';
import {Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, Typography} from '@mui/material';

interface CustomFirstDialogProps {
    open: boolean;
    onClose: () => void;
    onAction: (action: 'Add' | 'Remove') => void;
}

const CustomFirstDialog: React.FC<CustomFirstDialogProps> = ({open, onClose, onAction}) => {
    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Update Diagram</DialogTitle>
            <DialogContent>
                <Typography>Choose an action:</Typography>
                <Box sx={{display: 'flex', flexDirection: 'column', marginTop: '16px'}}>
                    <Button onClick={() => onAction('Add')} color="primary">
                        Add
                    </Button>
                    <Button onClick={() => onAction('Remove')} color="secondary">
                        Remove
                    </Button>
                </Box>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
            </DialogActions>
        </Dialog>
    );
};

export default CustomFirstDialog;
