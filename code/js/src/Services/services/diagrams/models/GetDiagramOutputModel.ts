import {SirenEntity} from "../../../media/siren/SirenEntity";

export interface GetDiagramOutputModel {
    entities: { [key: string]: string[] };
    relations: { [key: string]: { [key: string]: string } };
}

export type GetDiagramOutput = SirenEntity<GetDiagramOutputModel>