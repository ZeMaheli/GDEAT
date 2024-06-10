/**
 * Specification for representing hypermedia entities in JSON.
 *
 * @see <a href="https://www.iana.org/assignments/media-types/application/json">Json Specification</a>
 *
 * @property code the http status code of the error
 * @property message the message of the error
 * @property details detailed message of the error
 */
export interface JsonError {
    code: number;
    message: string;
    details?: string;
}

export class JsonErrorResponse implements JsonError {
    code: number;
    message: string;
    details?: string;

    constructor(code: number, message: string, details?: string) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
}

export const jsonErrorMediaType = "application/json"
