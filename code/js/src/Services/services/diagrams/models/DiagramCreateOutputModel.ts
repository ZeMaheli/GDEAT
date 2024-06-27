import {SirenEntity} from "../../../media/siren/SirenEntity";

/**
 * A Graph Creation Output Model.
 *
 * @property entities an object with entities names and their attributes
 * @property relations an object with the different relations between the entities
 */
interface DiagramCreateOutputModel {
    entities: { [key: string]: string[] };
    relations: { [key: string]: { [key: string]: string } };
}

export function createDiagramCreateOutputModel(data: DiagramCreateOutputModel): DiagramCreateOutputModel & { createNeatoDiagram: () => string } {
    return {
        ...data,
        createNeatoDiagram: function(): string {
            let graphCode = "graph ER {\n" +
                "\tfontname=\"Helvetica,Arial,sans-serif\"\n" +
                "\tnode [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                "\tedge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                "\tlayout=neato\n"

            // Define entity nodes
            graphCode += "\tnode [shape=box]; "
            for (const entity in this.entities) {
                graphCode += entity + ";"
            }

            for (const entity in this.entities) {
                this.entities[entity].forEach((atr: string, index: number) => {
                    let attributeName = `${entity}_attr${index}`;
                    graphCode += `\tnode [shape=ellipse, label=\"${atr}\"] ${attributeName};\n`
                    graphCode += `\t${entity} -- ${attributeName};\n`
                });
            }

            for (const relation in this.relations) {
                for (let relationMapKey in this.relations[relation]) {
                    graphCode += `${relation} -- ${relationMapKey} [label=\"${this.relations[relation][relationMapKey]}\"];`
                }
            }

            graphCode += ("\tlabel = \"Entity Relation Diagram\"\n")
            graphCode += ("\tfontsize=20\n")
            graphCode += ("}")

            return graphCode;
        }
    };
}

export type DiagramCreateOutput = SirenEntity<DiagramCreateOutputModel>