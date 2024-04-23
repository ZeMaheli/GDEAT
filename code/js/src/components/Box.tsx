import React from 'react'
import "../style/components.css";

export const Box = ({ value, onClick }) => {
    return (
        <button onClick={onClick} className="gameButtonStyle">
            {value}
        </button>
    );
};