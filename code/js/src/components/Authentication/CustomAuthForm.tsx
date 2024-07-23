import React from 'react';
import {Box, Button} from '@mui/material';
import {Link} from 'react-router-dom';


interface Field {
    id: string;
    name: string;
    label: string;
    type: 'text' | 'password';
}

interface CustomAuthFormProps {
    onSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
    isSubmitting: boolean;
    inputs: { [key: string]: string };
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    fields: Field[];
    submitButtonText: string;
    alternateActionText: string;
    alternateActionLink: string;
    alternateActionLinkText: string;
}

const CustomAuthForm: React.FC<CustomAuthFormProps> = (
    {
        onSubmit,
        isSubmitting,
        inputs,
        onChange,
        fields,
        submitButtonText,
        alternateActionText,
        alternateActionLink,
        alternateActionLinkText,
    }) => {
    return (
        <form onSubmit={onSubmit} style={{width: '100%'}}>
            <fieldset disabled={isSubmitting} style={{border: 'none'}}>
                {fields.map((field, index) => (
                    <Box key={index} sx={{marginBottom: '16px'}}>
                        <label htmlFor={field.id} style={{marginBottom: '8px', display: 'block'}}>
                            {field.label}
                        </label>
                        <input
                            id={field.id}
                            type={field.type}
                            name={field.name}
                            value={inputs[field.name]}
                            onChange={onChange}
                            style={{width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #ccc'}}
                        />
                    </Box>
                ))}
                <Box>
                    <Button
                        type="submit"
                        variant="contained"
                        color="primary"
                        style={{
                            width: '100%',
                            padding: '12px',
                            borderRadius: '4px',
                            backgroundColor: '#004d40',
                            color: '#fff',
                            border: 'none',
                            cursor: 'pointer'
                        }}
                    >
                        {isSubmitting ? `${submitButtonText}...` : submitButtonText}
                    </Button>
                </Box>
                <p style={{marginTop: '16px', textAlign: 'center'}}>
                    {alternateActionText}{' '}
                    <Link to={alternateActionLink} style={{color: '#00796b', textDecoration: 'none'}}>
                        {alternateActionLinkText}
                    </Link>
                </p>
            </fieldset>
        </form>
    );
};

export default CustomAuthForm;
