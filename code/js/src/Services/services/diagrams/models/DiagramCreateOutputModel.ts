import {SirenEntity} from "../../../media/siren/SirenEntity";

/**
 * A Graph Creation Output Model.
 *
 * @property diagramCode the code of the graph
 * @property diagramPDF the visualization of the graph
 */
interface DiagramCreateOutputModel {
    diagramCode: String,
    diagramPDF: Uint8Array
}

export type DiagramCreateOutput = SirenEntity<DiagramCreateOutputModel>