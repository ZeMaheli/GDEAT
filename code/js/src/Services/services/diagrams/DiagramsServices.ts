import {post} from "../../custom/useFetch";
import {DiagramCreateOutput} from "./models/DiagramCreateOutputModel";

export namespace DiagramsService {

    /**
     * Registers the user with the given email, username and password.
     *
     * @param createDiagramLink
     * @param prompt
     * @param signal the signal to cancel the request
     *
     * @return the API result of the register request
     */
    export async function createDiagram(
        createDiagramLink: string,
        prompt: string,
        signal?: AbortSignal
    ): Promise<DiagramCreateOutput> {
        return await post(createDiagramLink, JSON.stringify({prompt}), signal)
    }
}
