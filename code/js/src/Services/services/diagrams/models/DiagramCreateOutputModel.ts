import {SirenEntity} from "../../../media/siren/SirenEntity";


/**
 * A Graph Creation Output Model.
 *
 * @property entities an object with entities names and their attributes
 * @property relations an object with the different relations between the entities
 */
export interface DiagramCreateOutputModel {
    entities: { [key: string]: string[] };
    relations: { [key: string]: { [key: string]: string } };
}

export function createNeatoDiagram(data: DiagramCreateOutputModel) {
    let graphCode = "graph ER {\n" +
        "\tfontname=\"Helvetica,Arial,sans-serif\"\n" +
        "\tnode [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
        "\tedge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
        "\tlayout=neato\n" +
        "\tbgcolor=\"#f0f0f0\"\n"

    // Define entity nodes
    let entitiesCode = "\t\/*Entities*\/\n\tnode [shape=box]; "
    for (const entity in data.entities) {
        entitiesCode += entity + ";"
    }

    for (const entity in data.entities) {
        data.entities[entity].forEach((atr: string, index: number) => {
            let attributeName = `${entity}_attr${index}`;
            graphCode += `\tnode [shape=ellipse, label=\"${atr}\"] ${attributeName};\n`
            graphCode += `\t${entity} -- ${attributeName};\n`
        });
    }

    for (const relation in data.relations) {
        for (let relationMapKey in data.relations[relation]) {
            graphCode += `${relation} -- ${relationMapKey} [label=\"${data.relations[relation][relationMapKey]}\"];`
        }
    }

    graphCode += ("\tlabel = \"Entity Relation Diagram\"\n")
    graphCode += ("\tfontsize=20\n")
    graphCode += ("}")

    return graphCode;
}

export type DiagramCreateOutput = SirenEntity<DiagramCreateOutputModel>