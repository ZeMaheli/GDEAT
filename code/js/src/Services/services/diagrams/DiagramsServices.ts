import {DiagramCreateOutput} from "./models/DiagramCreateOutputModel";
import {post} from "../../utils/fetchSiren";
import {CREATE_DIAGRAM, DELETE_DIAGRAM, DIAGRAM_LIST, GET_DIAGRAM, SAVE_DIAGRAM} from "../../navigation/URIS";
import {GetDiagramOutput} from "./models/GetDiagramOutputModel";
import {SirenEntity} from "../../media/siren/SirenEntity";

export namespace DiagramsService {



    /**
     * Creates a diagram with the given prompt
     *
     * @param prompt
     * @param signal the signal to cancel the request
     *
     * @return the API result of the register request
     */
    export async function createDiagram(
        prompt: string,
        signal?: AbortSignal
    ): Promise<DiagramCreateOutput> {
        return await post(CREATE_DIAGRAM, JSON.stringify({prompt}), signal)
    }

    /**
     * Stores a diagram
     *
     * @param name
     * @param prompt
     * @param entities
     * @param relations
     * @param signal the signal to cancel the request
     *
     * @return the API result of the register request
     */
    export async function storeDiagram(
        name: string,
        prompt: string,
        entities: { [key: string]: string[] },
        relations: { [key: string]: { [key: string]: string } },
        signal?: AbortSignal
    ): Promise<unknown> {
        return await post(SAVE_DIAGRAM, JSON.stringify({name, prompt, entities, relations}), signal)
    }

    /**
     * Stores a diagram
     *
     * @param name
     * @param signal the signal to cancel the request
     *
     * @return the API result of the register request
     */
    export async function deleteDiagram(
        name: string,
        signal?: AbortSignal
    ): Promise<DiagramCreateOutput> {
        return await post(DELETE_DIAGRAM, JSON.stringify({name}), signal)
    }

    /**
     * Stores a diagram
     *
     * @param name
     * @param signal the signal to cancel the request
     *
     * @return the API result of the register request
     */
    export async function getDiagram(
        name: string,
        signal?: AbortSignal
    ): Promise<GetDiagramOutput> {
        return await post(GET_DIAGRAM, JSON.stringify({name}), signal)
    }

    /**
     * Stores a diagram
     * @param signal the signal to cancel the request
     *
     * @return the API result of the register request
     */
    export async function getDiagrams(
        signal?: AbortSignal
    ): Promise<DiagramCreateOutput> {
        return await post(DIAGRAM_LIST, undefined, signal)
    }
}
