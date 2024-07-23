import {SirenEntity} from "../../../media/siren/SirenEntity";
import {GetDiagramOutputModel} from "./GetDiagramOutputModel";


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

export function createNeatoDiagram(data: DiagramCreateOutputModel | GetDiagramOutputModel) {
    let diagramCode = "graph ER {\n" +
        "\tfontname=\"Helvetica,Arial,sans-serif\"\n" +
        "\tnode [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
        "\tedge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
        "\tlayout=neato\n" +
        "\tbgcolor=\"#f0f0f0\"\n"

    // Define entity nodes
    diagramCode += "\t\/*Entities*\/\n\tnode [shape=box, width=1.5, height=0.75];\n"
    for (const entity in data.entities) {
        diagramCode += `\t${entity};\n`
    }
    diagramCode += "\n"

    // Define attributes nodes and its relations
    let relationsDOTCode = ""
    let defineAttributesCode = "\t\/*Attributes*\/\n\tnode [shape=ellipse];\n"
    for (const entity in data.entities) {
        data.entities[entity].forEach((atr: string) => {
            const attributeName = `${atr}_${entity}`
            defineAttributesCode += `\t${attributeName};\n`
            relationsDOTCode += `\t${attributeName} -- ${entity} [len=3]\n`
        });
    }

    // Define relations and connect them
    let defineRelationsDOTCode = "\t\/*Relations*\/\n\tnode [shape=diamond,style=filled,color=lightgrey];\n"
    let relations = new Map<string, string[]>()
    let relationsDefinition: string[] = [];
    for (const firstEntity in data.relations) {
        relations.set(firstEntity, [])
        const firstEntityRelation = firstEntity.substring(0, 2)
        for (let secondEntity in data.relations[firstEntity]) {
            relations.set(secondEntity, [])
            const relation = `\"${firstEntityRelation}-${secondEntity.substring(0, 2)}\"`
            const relationInverse = reverseString(relation)
            const fERelations = relations.get(firstEntity)

            if (!relationsDefinition.includes(relation) && !relationsDefinition.includes(relationInverse)) {
                relationsDefinition.push(relation)
                defineRelationsDOTCode += `\t${relation};\n`
            }

            const firstRelationNumeral = data.relations[firstEntity][secondEntity].substring(0, 1)
            const secondRelationNumeral = data.relations[firstEntity][secondEntity].substring(2, 3)

            if (fERelations) {
                if (!fERelations.includes(relation) && !fERelations.includes(relationInverse)) {
                    fERelations.push(relation)
                    relations.set(firstEntity, fERelations)
                    relationsDOTCode += `\t${firstEntity} -- ${relation} [taillabel=\"${firstRelationNumeral}\", len=3, labeldistance=2];\n`
                }
            }
            const sERelations = relations.get(secondEntity)
            if (sERelations) {
                if (!sERelations.includes(relation) && !sERelations.includes(relationInverse)) {
                    sERelations.push(relation)
                    relations.set(firstEntity, sERelations)
                    relationsDOTCode += `\t${relation} -- ${secondEntity} [taillabel=\"${secondRelationNumeral}\", len=3, labeldistance=2];\n`
                }
            }
        }
    }

    diagramCode += defineAttributesCode + "\n"
    diagramCode += defineRelationsDOTCode + "\n"
    diagramCode += relationsDOTCode

    diagramCode += ("\tlabel = \"Entity-Relation Diagram\"\n")
    diagramCode += ("\tfontsize=20\n")
    diagramCode += ("}")

    return diagramCode;
}

function reverseString(str1: string): string {
    const parts = str1.split('-');
    parts.reverse();
    return parts.join('-');
}

export type DiagramCreateOutput = SirenEntity<DiagramCreateOutputModel>