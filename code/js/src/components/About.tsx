import React, {useEffect, useState} from 'react';
import {about} from "../services/usersServices";

export default function About() {
    const [aboutData, setAboutData] = useState<any>('');

    useEffect(() => {
        about()
            .then((data) => {
                setAboutData(data)
            })
    }, [])

    return (
        <div style={containerStyle}>
            <h1 style={headerStyle}>About:</h1>
            {aboutData ? (
                <div style={contentStyle}>
                    <p>
                        <strong>Version:</strong> {aboutData.version}
                    </p>
                    <p>
                        <strong>Authors:</strong>
                    </p>
                    <ul style={listStyle}>
                        {aboutData.authors.map((author: string, index: number) => (
                            <li key={index} style={listItemStyle}>
                                {author}
                            </li>
                        ))}
                    </ul>
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
};

const containerStyle: React.CSSProperties = {
    textAlign: 'center',
    marginTop: '50px',
};

const headerStyle: React.CSSProperties = {
    color: '#ffffff',
};

const contentStyle: React.CSSProperties = {
    color: '#9d9d9d',
    maxWidth: '400px',
    margin: 'auto',
    padding: '20px',
    backgroundColor: 'rgba(45,44,44,0.5)',
    borderRadius: '8px',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
};

const listStyle: React.CSSProperties = {
    listStyleType: 'none',
    padding: '0',
};

const listItemStyle: React.CSSProperties = {
    color: '#9d9d9d',
    borderBottom: '1px solid #ccc',
    padding: '10px',
};
