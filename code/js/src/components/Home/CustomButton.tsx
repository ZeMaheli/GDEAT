import React from 'react';
import Button from '@mui/material/Button';

interface CustomButtonProps {
    label: string;
    onClick: (event: React.MouseEvent<HTMLButtonElement>) => void;
    disabled?: boolean;
}

const CustomButton: React.FC<CustomButtonProps> = ({label, onClick, disabled}) => {
    const [isHovered, setIsHovered] = React.useState(false);

    const handleHover = (event: React.MouseEvent<HTMLButtonElement>) => {
        setIsHovered(event.type === 'mouseenter');
    };

    return (
        <Button
            variant="contained"
            color="primary"
            onClick={onClick}
            disabled={disabled}
            onMouseEnter={handleHover}
            onMouseLeave={handleHover}
            style={{
                width: '30%',
                marginRight: '8px',
                color: '#444444',
                alignSelf: 'center',
                backgroundColor: isHovered
                    ? '#e6e6e6'
                    : '#FFFFFF'
            }}
        >
            {label}
        </Button>
    );
};

export default CustomButton;
