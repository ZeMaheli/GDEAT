import React, {useEffect, useRef, useState} from 'react';
import {DiagramsService} from "../../Services/services/diagrams/DiagramsServices";
import {Box} from "@mui/material";
import {instance} from '@viz-js/viz';
import {
    createNeatoDiagram,
    DiagramCreateOutputModel
} from '../../Services/services/diagrams/models/DiagramCreateOutputModel';
import panzoom from 'panzoom';
import CustomButton from "./CustomButton";
import CustomTextField from "./CustomTextField";
import jsPDF from "jspdf";
import {useSessionManager} from "../../Utils/Session";
import CustomFirstDialog from "./CustomFirstDialog";
import CustomSecondDialog from "./CustomSecondDialog";
import CustomThirdDialog from "./CustomThirdDialog";
import CustomFourthDialog from "./CustomFourthDialog";
import CustomFifthDialog from "./CustomFifthDialog";
import CustomSixthDialog from "./CustomSixthDialog";
import createDiagram = DiagramsService.createDiagram;

export default function Home(): React.ReactElement {
    type DialogAction = 'Add' | 'Remove';
    type AddAction = 'Entity' | 'Relation';
    let prompTextPlaceholder = "[Insert Text]"
    let prompt = `{"prompt": "${prompTextPlaceholder}"}`;

    const sessionManager = useSessionManager();

    const [error, setError] = useState<string>('');
    const [text, setText] = useState<string>("");
    const [submitting, setSubmitting] = useState<boolean>(false);
    const [dotCode, setDotCode] = useState<string>('');
    const [data, setData] = useState<DiagramCreateOutputModel>({entities: {}, relations: {}});
    const svgContainerRef = useRef<HTMLDivElement>(null);
    const svgElementRef = useRef<SVGElement | null>(null);

    const [openFirstDialog, setOpenFirstDialog] = useState(false);
    const [openAddDialog, setOpenAddDialog] = useState(false);
    const [openRemoveDialog, setOpenRemoveDialog] = useState(false);
    const [openAddEntityDialog, setOpenAddEntityDialog] = useState(false);
    const [openRemoveEntityDialog, setOpenRemoveEntityDialog] = useState(false);
    const [openAddRelationDialog, setOpenAddRelationDialog] = useState(false);
    const [openRemoveRelationDialog, setOpenRemoveRelationDialog] = useState(false);

    const [dialogAction, setDialogAction] = useState<DialogAction | null>(null);
    const [addAction, setAddAction] = useState<AddAction | null>(null);
    const [removeAction, setRemoveAction] = useState<AddAction | null>(null);

    const [entityName, setEntityName] = useState('');
    const [entityAttributes, setEntityAttributes] = useState('');

    const [firstEntityName, setFirstEntityName] = useState('');
    const [relationName, setRelationName] = useState('');
    const [secondEntityName, setSecondEntityName] = useState('');

    const handleExportDiagram = () => {
        // Create a new jsPDF document
        const doc = new jsPDF();

        // Get the SVG element
        const svgElement = svgElementRef.current;

        if (!svgElement) {
            setError("SVG element is null");
            return; // Or handle the error as needed
        }
        // Convert SVG to a data URL (base64 encoded)
        const svgData = new XMLSerializer().serializeToString(svgElement);
        const canvas = document.createElement('canvas');
        const context = canvas.getContext('2d');

        // Create an Image object from the SVG data
        const image = new Image();
        image.onload = () => {
            canvas.width = image.width;
            canvas.height = image.height;
            if (context) {
                context.drawImage(image, 0, 0);
            } else {
                setError('Failed to get canvas context');
            }

            // Add the image to the PDF
            const imgData = canvas.toDataURL('image/png', 1.0);
            doc.addImage(imgData, 'JPEG', 10, 10, doc.internal.pageSize.getWidth() - 20, 0);

            // Save the PDF
            doc.save('diagram.pdf');
        };
        image.src = 'data:image/svg+xml;base64,' + btoa(svgData);
    };

    useEffect(() => {
        if (svgElementRef.current) {
            panzoom(svgElementRef.current, {
                bounds: true,
                boundsPadding: 0.1,
                maxZoom: 3,
                minZoom: 0.5,
            });
        }
    }, [svgElementRef.current]);

    const addEntity = (entity: string, entityData: string[]) => {
        setData(prevData => {
            const newEntities = {
                ...prevData.entities,
                [entity]: [...entityData] // Create a new array for entityData
            };
            return {
                ...prevData,
                entities: newEntities
            };
        });
        const code = createNeatoDiagram(data)
        setDotCode(code)
        instance().then(viz => {
            let diagramSVG = viz.renderSVGElement(code);
            setDotCode(code)
            const container = svgContainerRef.current;
            if (container) {
                container.innerHTML = '';
                container.appendChild(diagramSVG);
                svgElementRef.current = diagramSVG;
                panzoom(svgElementRef.current, {
                    bounds: true,
                    boundsPadding: 0.1,
                    maxZoom: 3,
                    minZoom: 0.5,
                });
            }

            setSubmitting(false);
        });
    };

    const removeEntity = (entity: string) => {
        if (data.entities[entity]) {
            var newMap = data
            delete newMap.entities[entity]
            setData(newMap)
            const code = createNeatoDiagram(data)
            setDotCode(code)
            instance().then(viz => {
                let diagramSVG = viz.renderSVGElement(code);
                setDotCode(code)
                const container = svgContainerRef.current;
                if (container) {
                    container.innerHTML = '';
                    container.appendChild(diagramSVG);
                    svgElementRef.current = diagramSVG;
                    panzoom(svgElementRef.current, {
                        bounds: true,
                        boundsPadding: 0.1,
                        maxZoom: 3,
                        minZoom: 0.5,
                    });
                }

                setSubmitting(false);
            });
        }
    }

    const addRelation = (entity1: string, relation: string, entity2: string) => {
        if (data.entities[entity1] && data.entities[entity2]) {
            var newMap = data
            newMap.relations[entity1] = {}
            newMap.relations[entity1][entity2] = relation
            setData(newMap)
            const code = createNeatoDiagram(data)
            setDotCode(code)
            instance().then(viz => {
                let diagramSVG = viz.renderSVGElement(code);
                setDotCode(code)
                const container = svgContainerRef.current;
                if (container) {
                    container.innerHTML = '';
                    container.appendChild(diagramSVG);
                    svgElementRef.current = diagramSVG;
                    panzoom(svgElementRef.current, {
                        bounds: true,
                        boundsPadding: 0.1,
                        maxZoom: 3,
                        minZoom: 0.5,
                    });
                }

                setSubmitting(false);
            });
        }
    }

    const removeRelation = (entity1: string, entity2: string) => {
        if (data.entities[entity1] && data.entities[entity2]) {
            var newMap = data
            delete newMap.relations[entity1][entity2]
            setData(newMap)
            const code = createNeatoDiagram(data)
            setDotCode(code)
            instance().then(viz => {
                let diagramSVG = viz.renderSVGElement(code);
                setDotCode(code)
                const container = svgContainerRef.current;
                if (container) {
                    container.innerHTML = '';
                    container.appendChild(diagramSVG);
                    svgElementRef.current = diagramSVG;
                    panzoom(svgElementRef.current, {
                        bounds: true,
                        boundsPadding: 0.1,
                        maxZoom: 3,
                        minZoom: 0.5,
                    });
                }

                setSubmitting(false);
            });
        }
    }

    // Update Diagram
    const handleOpenFirstDialog = () => {
        setOpenFirstDialog(true);
    };
    const handleCloseFirstDialog = () => {
        setOpenFirstDialog(false);
    };

    //Add to diagram
    const handleOpenAddDialog = () => {
        setOpenAddDialog(true);
    };
    const handleCloseAddDialog = () => {
        setOpenAddDialog(false);
    };

    //Remove from diagram
    const handleOpenRemoveDialog = () => {
        setOpenRemoveDialog(true);
    };
    const handleCloseRemoveDialog = () => {
        setOpenRemoveDialog(false);
    };


    const handleOpenAddEntityDialog = () => {
        setOpenAddEntityDialog(true);
    };
    const handleCloseAddEntityDialog = () => {
        setOpenAddEntityDialog(false);
    };
    const handleOpenRemoveEntityDialog = () => {
        setOpenRemoveEntityDialog(true);
    };
    const handleCloseRemoveEntityDialog = () => {
        setOpenRemoveEntityDialog(false);
    };

    const handleOpenAddRelationDialog = () => {
        setOpenAddRelationDialog(true);
    };
    const handleCloseAddRelationDialog = () => {
        setOpenAddRelationDialog(false);
    };
    const handleOpenRemoveRelationDialog = () => {
        setOpenRemoveRelationDialog(true);
    };
    const handleCloseRemoveRelationDialog = () => {
        setOpenRemoveRelationDialog(false);
    };

    const handleDialogAction = (action: DialogAction) => {
        setDialogAction(action);
        handleCloseFirstDialog();
        if (action === 'Add') {
            handleOpenAddDialog();
        }
        if (action === 'Remove') {
            handleOpenRemoveDialog();
        }
    };

    const handleRemoveAction = (action: AddAction) => {
        setRemoveAction(action);
        handleCloseRemoveDialog();
        if (action === 'Entity') {
            handleOpenRemoveEntityDialog();
        } else {
            handleOpenRemoveRelationDialog();
        }
    };

    const handleAddAction = (action: AddAction) => {
        setAddAction(action);
        handleCloseAddDialog();
        if (action === 'Entity') {
            handleOpenAddEntityDialog();
        } else {
            handleOpenAddRelationDialog();
        }
    };

    const handleAddRelation = () => {
        if (firstEntityName.trim() === '' || secondEntityName.trim() === '' || relationName.trim() === '') {
            return;
        }
        addRelation(firstEntityName, relationName, secondEntityName);
        handleCloseAddRelationDialog();
    };
    const handleRemoveRelation = () => {
        if (firstEntityName.trim() === '' || secondEntityName.trim() === '') {
            return;
        }
        removeRelation(firstEntityName, secondEntityName);
        handleCloseRemoveRelationDialog();
    };

    const handleAddEntity = () => {
        if (entityName.trim() === '' || entityAttributes.trim() === '') {
            return;
        }
        const attributesArray = entityAttributes.split(',').map(attr => attr.trim()).filter(attr => attr.length > 0);
        addEntity(entityName, attributesArray);
        handleCloseAddEntityDialog();
    };

    const handleRemoveEntity = () => {
        if (entityName.trim() === '') {
            return;
        }
        removeEntity(entityName);
        handleCloseRemoveEntityDialog();
    };

    const handleEntityNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setEntityName(event.target.value);
    };

    const handleEntityAttributesChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setEntityAttributes(event.target.value);
    };

    const handleFirstEntityNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFirstEntityName(event.target.value);
    };

    const handleSecondEntityNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSecondEntityName(event.target.value);
    };

    const handleRelationNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setRelationName(event.target.value);
    };

    const handleCodeEdit = () => {
        instance().then(viz => {
            let diagramSVG = viz.renderSVGElement(dotCode);
            const container = svgContainerRef.current;
            if (container) {
                container.innerHTML = '';
                container.appendChild(diagramSVG);
                svgElementRef.current = diagramSVG;
                panzoom(svgElementRef.current, {
                    bounds: true,
                    boundsPadding: 0.1,
                    maxZoom: 3,
                    minZoom: 0.5,
                });
            }
        });
    }

    const handleCreateGraph = async () => {
        try {
            setSubmitting(true);
            const response = await createDiagram(prompt.replace(prompTextPlaceholder, text));
            const prop = response.properties;
            if (prop) {
                setData(prop)
                const graphCode = createNeatoDiagram(prop);
                setDotCode(graphCode)
                instance().then(viz => {
                    let diagramSVG = viz.renderSVGElement(graphCode);
                    setDotCode(graphCode)
                    const container = svgContainerRef.current;
                    if (container) {
                        container.innerHTML = '';
                        container.appendChild(diagramSVG);
                        svgElementRef.current = diagramSVG;
                        panzoom(svgElementRef.current, {
                            bounds: true,
                            boundsPadding: 0.1,
                            maxZoom: 3,
                            minZoom: 0.5,
                        });
                    }

                    setSubmitting(false);
                });
            }
        } catch (error) {
            console.error('Graph creation failed', error);
            setError((error as Error).message);
            setSubmitting(false);
        }
    };

    // @ts-ignore
    return (
        <>
            <Box className="container" sx={{display: 'flex', flexDirection: 'column', gridColumn: '1'}}>
                <CustomTextField label={"Prompt"} value={text} onChange={setText}
                                 infoText={"Textual description of the problem"}/>
                <CustomButton
                    label="Create Diagram"
                    onClick={handleCreateGraph}
                    disabled={submitting}
                />
            </Box>
            <Box className="container" sx={{display: 'flex', flexDirection: 'column', gridColumn: '2'}}>
                <CustomTextField label={"Dot Code"} value={dotCode} onChange={setDotCode}
                                 infoText={"Plain text diagram description language used to define the structure and attributes of diagrams"}/>
                <CustomButton
                    label="Update Code"
                    onClick={handleCodeEdit}
                    disabled={submitting || dotCode.length === 0}
                />
            </Box>
            <Box className="container"
                 sx={{gridColumn: 'span 2', display: 'flex', flexDirection: 'column', marginTop: '16px'}}>
                <Box className="bottom" ref={svgContainerRef} style={{
                    padding: '16px',
                    backgroundColor: '#f0f0f0',
                    overflow: 'hidden',
                    borderTop: '1px solid #ddd',
                    position: 'relative',
                    marginTop: '16px',
                    height: '400px',
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    borderRadius: '8px'
                }}/>
                <Box sx={{display: 'flex', justifyContent: 'center', marginTop: '16px'}}>
                    <CustomButton
                        label="Update Diagram"
                        onClick={handleOpenFirstDialog}
                        disabled={submitting || dotCode.length === 0}
                    />
                    <CustomButton
                        label="Export Diagram"
                        onClick={handleExportDiagram}
                        disabled={submitting || dotCode.length === 0}
                    />
                </Box>
            </Box>
            <CustomFirstDialog
                open={openFirstDialog}
                onClose={handleCloseFirstDialog}
                onAction={handleDialogAction}
            />
            <CustomSecondDialog
                open={openAddDialog}
                onClose={handleCloseAddDialog}
                onSelect={handleAddAction}
            />
            <CustomSecondDialog
                open={openRemoveDialog}
                onClose={handleCloseRemoveDialog}
                onSelect={handleRemoveAction}/>
            <CustomThirdDialog
                open={openAddEntityDialog}
                onClose={handleCloseAddEntityDialog}
                onAddEntity={handleAddEntity}
                entityName={entityName}
                entityAttributes={entityAttributes}
                onEntityNameChange={handleEntityNameChange}
                onEntityAttributesChange={handleEntityAttributesChange}
            />
            <CustomFourthDialog
                open={openRemoveEntityDialog}
                onClose={handleCloseRemoveEntityDialog}
                onRemoveEntity={handleRemoveEntity}
                entityName={entityName}
                onEntityNameChange={handleEntityNameChange}
            />
            <CustomFifthDialog open={openAddRelationDialog} onClose={handleCloseAddRelationDialog}
                               onAddRelation={handleAddRelation} firstEntityName={firstEntityName}
                               secondEntityName={secondEntityName} relationName={relationName}
                               onFirstEntityNameChange={handleFirstEntityNameChange}
                               onSecondEntityChange={handleSecondEntityNameChange}
                               onRelationNameChange={handleRelationNameChange}/>
            <CustomSixthDialog open={openRemoveRelationDialog} onClose={handleCloseRemoveRelationDialog}
                               onRemoveRelation={handleRemoveRelation} firstEntityName={firstEntityName}
                               secondEntityName={secondEntityName} onFirstEntityNameChange={handleFirstEntityNameChange}
                               onSecondEntityChange={handleSecondEntityNameChange}/>
        </>
    );
}

